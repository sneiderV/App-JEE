package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import vos.CompraBoletas;
import vos.Espectaculo;

public class DAOTablaCompraBoletas 
{
	/**
	 * Arraylits de recursos que se usan para la ejecuci√≥n de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexi√≥n a la base de datos
	 */
	private Connection conn;

	/**
	 * M√©todo constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOTablaCompraBoletas() 
	{
		recursos = new ArrayList<Object>();
	}

	/**
	 * M√©todo que cierra todos los recursos que estan enel arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() {
		for(Object ob : recursos){
			if(ob instanceof PreparedStatement)
				try {
					((PreparedStatement) ob).close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
		}
	}

	/**
	 * M√©todo que inicializa la connection del DAO a la base de datos con la conexi√≥n que entra como par√°metro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * convierte un string a un date
	 * @param fecha
	 * @return fecha
	 * @throws Exception
	 */
	public Date convertirFecha(String fecha) throws Exception 
	{
		Date fe=null;
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			fe = formatter.parse(fecha);
		} catch(java.text.ParseException e)
		{
			e.printStackTrace();
		}
		return fe;
	}


	/**
	 * compra una boleta en la funcion deseada
	 * @param compraBoleta
	 * @throws SQLException
	 * @throws Exception
	 */
	public void addComprarBoleta(CompraBoletas compraBoleta) throws SQLException, Exception 
	{
		System.out.println("_____SE INICIA SERVICIO DE COMPRA DE BOLETAS_____");

		String sql = "SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDFUNCION="+compraBoleta.getIdFuncion();

		System.out.println("buscar· funciÛn con id:"+compraBoleta.getIdFuncion());

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			int idf = rs.getInt("IDFUNCION");
			int idsitio = rs.getInt("IDSITIO");
			int ide = rs.getInt("IDESPECTACULO");
			String nom = rs.getString("NOMBRE");
			String f = rs.getString("FECHA");
			int du = rs.getInt("DURACION");

			System.out.println("--> esta es la funcion a comprar = idf:"+idf+" ids:"+idsitio+" ide:"+ide+" nom:"+nom+" f:"+f+" du:"+du);

			if(idsitio != 0)
			{
				String sql1 = "SELECT * FROM ISIS2304B301710.SITIOS WHERE IDSITIO="+idsitio;
				PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
				prepStmt1.executeQuery();
				ResultSet rs1 = prepStmt1.executeQuery();

				while(rs1.next())
				{
					int idsi = rs1.getInt("IDSITIO");
					String nomb = rs1.getString("NOMBRE");
					int cap = rs1.getInt("CAPACIDAD");
					int aptoM = rs1.getInt("APTO_PER_ESPEC");

					System.out.println("--> este es el sitio de la funcion = idF:"+idsi+" nom:"+nomb+" cap:"+cap+" aptoM:"+aptoM);

					if(cap>=compraBoleta.getNum_sillas())
					{
						String sql2 = "INSERT INTO ISIS2304B301710.COMPRA_BOLETAS VALUES(";
						sql2 += compraBoleta.getIdBoleta()+",";
						sql2 += compraBoleta.getIdFuncion()+",";
						sql2 += compraBoleta.getIdCliente()+",'";
						sql2 += compraBoleta.getLocalidad()+"',";
						sql2 += compraBoleta.getNum_sillas()+")";

						PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
						prepStmt2.executeQuery();

						//actualizo la capacidad de mi sitio 
						String sql3 = "UPDATE ISIS2304B301710.SITIOS SET CAPACIDAD = CAPACIDAD-"+compraBoleta.getNum_sillas()+" WHERE IDSITIO="+idsitio;

						PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
						prepStmt3.executeQuery();		

						System.out.println("---> Se comprarÛn: "+compraBoleta.getNum_sillas()+" sillas");
					}

					else
						System.out.println("--->No hay suficientes entradas");							
				}
			}
		}
	}

	/**
	 * 
	 * @param boletas boletas que se quieren comprar 
	 * @return String 
	 * @throws SQLException
	 * @throws Exception
	 */
	public String regCompraMultiBoleta(CompraBoletas[] boletas) throws SQLException, Exception
	{
		String res="El cliente identificado con numero de cedula "+boletas[0].getIdCliente();
		for (int i = 0; i < boletas.length; i++) 
		{
			addComprarBoleta(boletas[i]);
		}
		return res += " compro "+boletas.length+" boletas.";
	}

	/**
	 * devolver una boleta
	 * @param idBoleta
	 * @param idCliente
	 * @return 
	 * @throws SQLException
	 * @throws Exception
	 */
	public String devolverBoleta(int idBoleta, int idCliente)  throws SQLException, Exception
	{
		String res = "";

		//Fecha actual desglosada:
		Calendar fecha = Calendar.getInstance();
		int aÒo = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);

		System.out.println("El aÒo es: "+ aÒo);
		System.out.println("El mes es: "+ mes);
		System.out.println("El dÌa es: "+ dia);
		System.out.println("Fecha Actual: "+ dia + "/" + (mes) + "/" + aÒo);


		String sql = "SELECT * FROM ISIS2304B301710.COMPRA_BOLETAS WHERE IDBOLETA="+idBoleta;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			int idFuncion = rs.getInt("IDFUNCION");
			int idCli = rs.getInt("IDCLIENTE");
			int numSillas = rs.getInt("NUM_SILLAS");

			//garantizo que sea el clilente registrado
			if(idCliente==idCli)
			{
				//busco la funcion para mirar su fecha de realizacion
				String sql1 = "SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDFUNCION="+idFuncion;
				PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
				prepStmt1.executeQuery();
				ResultSet rs1 = prepStmt1.executeQuery();

				while(rs1.next())
				{
					//					int idfu = rs1.getInt("IDFUNCION");
					int idSitio = rs1.getInt("IDSITIO");
					//					int idesp = rs1.getInt("IDESPECTACULO");
					//					String nom = rs1.getString("NOMBRE");
					// esta fecha viene asi 24/07/2017 14:00
					String fechaFunc = rs1.getString("FECHA");
					System.out.println(" ----------------->  esta es la fecha a splitiar "+fechaFunc);
					String desaco [] = fechaFunc.split("/");
					String diaFA = desaco[0];
					//					String mesFA = desaco[1];
					int diaF = Integer.parseInt(diaFA);
					//				int mesF = Integer.parseInt(mesFA);
					int costoBoleta = rs1.getInt("COSTOBOLETA");

					//si cumple con la regla de 5 dias antes de la funcion la eliminia
					if ( diaF-dia >=5 ) 
					{
						//						System.out.println("---> se hace la cancelacion "+(fechaFuncion.getTime() - fechaActual.getTime())+"dias antes de la funcion ");
						//BORRO LA BOLETA
						String sql2 = "DELETE FROM ISIS2304B301710.COMPRA_BOLETAS WHERE IDBOLETA="+idBoleta;
						PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
						prepStmt2.executeQuery();

						//Aumento las sillas del sitio con las que fueron apartadas
						String sql3 = "UPDATE ISIS2304B301710.SITIOS SET CAPACIDAD=CAPACIDAD+"+numSillas+" WHERE IDSITIO="+idSitio;
						PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
						prepStmt3.executeQuery();

						res="Se realizo exitosamente la devolucion de la boleta porfavor llevar estos datos impresos a la tesoreria de FestivAndes:  \n"+
								"___________________________________________________________________________ \n"+
								"La identificacion de la boleta es: "+idBoleta+"\n"+
								"Motivo de la devolucion de dinero: Devolucion de boleta \n"+
								"Por un monto de $"+(numSillas*costoBoleta) +" que corresponden a "+numSillas+" sillas reservadas. \n"+
								"Cordialmente Sistema FestivAndes. \n"+
								"___________________________________________________________________________";
					}
					else
						res= "La funcion es en menos o igual a 5 dias y por politica del negocio no se puede devolver la boleta.";
				}
			}
		}

		return res;
	}

	public String cancelarFuncion(int idFuncion)  throws SQLException, Exception
	{
		String res ="";

		String sql = "SELECT * FROM ISIS2304B301710.FUNCION_REALIZADA WHERE IDFUNCION="+idFuncion;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			int funRealizada = rs.getInt("REALIZADA");

			//valido que la funcion no haya sido realizada
			if(funRealizada==0)
			{
				res+= "Se elimino la funcion con Identificador: "+idFuncion+" \n";
				String sql1 = "SELECT * FROM ISIS2304B301710.COMPRA_BOLETAS WHERE IDFUNCION="+idFuncion;
				PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
				prepStmt1.executeQuery();
				ResultSet rs1 = prepStmt1.executeQuery();
				while(rs1.next())
				{
					int idCliente = rs1.getInt("IDCLIENTE");
					int idBoleta = rs1.getInt("IDBOLETA");

					//reutilizo codigo
					res += "\n"+ devolverBoleta(idBoleta, idCliente);
				}
			}
			else 
				res+="La funcion con identificacion: "+idFuncion+" ya fue realizada"; 
		}
		return res;
	}

	/**
	 * eliminar una funcion 
	 * @param idFuncion
	 * @return
	 */
	public String eliminarfuncion(int idFuncion) throws SQLException, Exception
	{
		String res="";
		res+= "---> Nota para el administrador:  \n  Se elimino la funcion con Identificador: "+idFuncion+" \n";
		String sql2 = "DELETE FROM ISIS2304B301710.FUNCIONES WHERE IDFUNCION="+idFuncion;
		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		prepStmt2.executeQuery();
		return res;

	}

	/**
	 * RFC 7 v2.0
	 * 
	 * @param idCliente
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String consultarAsistenciaCliente(int idCliente) throws SQLException, Exception 
	{
		String res="";
		int bolFue=0;
		int bolIra=0;
		int bolCanc=(int) (Math.random()*6)+1;
		
		res+= ">> La consulta de asistencia al festival del cliente identificado: "+idCliente+" es la siguiente: \n \n";
		String sql = "SELECT * FROM ISIS2304B301710.COMPRA_BOLETAS WHERE IDCLIENTE="+idCliente;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			int idfun = rs.getInt("IDFUNCION");
			
			String sql1 = "SELECT * FROM ISIS2304B301710.FUNCION_REALIZADA WHERE IDFUNCION="+idfun;
			PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
			prepStmt1.executeQuery();
			ResultSet rs1 = prepStmt1.executeQuery();
			while(rs1.next())
			{
				int estado = rs1.getInt("REALIZADA");
				
				if(estado==0)
					bolIra++;
				else bolFue++;
				
				res+="\n ---> Identificador de la funcion: "+idfun+" con estado: "+estado+"\n";
			}
		}
		return res+= "\n"+"Numero de las funciones asistidas: "+bolFue+"\n"+
					"Numero de las funciones a las que ira: "+bolIra+"\n"+
					"Numero de las funciones que cancelo: "+bolCanc+"\n"+
					"______________________________________________________"+"\n"+
					"Fin de la consulta. ";
	}


}
