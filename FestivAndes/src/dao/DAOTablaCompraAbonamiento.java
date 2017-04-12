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

public class DAOTablaCompraAbonamiento 
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
	public DAOTablaCompraAbonamiento() 
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
	public Date stringToDate(String fecha) throws Exception 
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
	 * se compra un abonamiento 
	 * @param compraBoleta
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean addComprarAbonamiento(CompraBoletas compraBoleta) throws SQLException, Exception 
	{
		boolean siRealizocompra = false;
		System.out.println("_____SE INICIA SERVICIO DE COMPRA DE BONAMIENTO_____");

		//Fecha actual desglosada:
		Calendar fecha = Calendar.getInstance();
		int aÒo = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int numeroSemanaCompra = fecha.get(Calendar.WEEK_OF_YEAR);

		System.out.println("El aÒo es: "+ aÒo);
		System.out.println("El mes es: "+ mes);
		System.out.println("El dÌa es: "+ dia);
		System.out.println("El numero de la semana es: "+numeroSemanaCompra);
		System.out.println("Fecha Actual: "+ dia + "/" + (mes) + "/" + aÒo);


		// Busco la funcion que se quiere registrar 
		String sql = "SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDFUNCION="+compraBoleta.getIdFuncion();
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

			Calendar fechaFuncion = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			fechaFuncion.setTime(sdf.parse(f));
			int numeroSemanaFuncion = fechaFuncion.get(Calendar.WEEK_OF_YEAR);

			//			System.out.println("--> esta es la funcion a comprar = idf:"+idf+" ids:"+idsitio+" ide:"+ide+" nom:"+nom+" f:"+f+" du:"+du);

			System.out.println("la semana de compra es: "+numeroSemanaCompra+" y la semana de la funcion es: "+numeroSemanaFuncion);
			//   		Miro que se cumpla antes de 3 semanas
			if(numeroSemanaFuncion-numeroSemanaCompra>=3)
			{
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
							//REGISTRO LA COMPRA DEL ABONAMIENTO 
							String sql2 = "INSERT INTO ISIS2304B301710.COMPRA_ABONAMIENTO VALUES(";
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

							siRealizocompra = true;

							System.out.println("---> Se comprarÛn: "+compraBoleta.getNum_sillas()+" sillas");
						}

						else
							System.out.println("--->No hay suficientes entradas");							
					}
				}
			}

			else 
				System.out.println("No puede acceder al abonamiento por que faltan "+(numeroSemanaFuncion-numeroSemanaCompra)+" semanas para el inicio del evento!");
		}

		return siRealizocompra;
	}

	public String regAbonamiento(CompraBoletas[] boletas)  throws SQLException, Exception 
	{
		String res="El cliente identificado con numero de cedula "+boletas[0].getIdCliente();
		int numboletasCompradas =0;
		for (int i = 0; i < boletas.length; i++) 
		{
			if (addComprarAbonamiento(boletas[i]) == true)
			{
				numboletasCompradas++;
			}
		}
		return res += " compro "+numboletasCompradas+" boletas con el descuento del 20% en todas sus boletas.";

	}

	/**
	 * devuelve un abonamiento
	 * @param idCliente
	 * @param idBoleta
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public int[] devolverAbonamiento(int idCliente, int idBoleta) throws SQLException, Exception
	{
		int[] res = new int[4];

		//Fecha actual desglosada:
		Calendar fecha = Calendar.getInstance();
		int aÒo = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH) + 1;
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int numeroSemanaEliminar = fecha.get(Calendar.WEEK_OF_YEAR);


		System.out.println("El aÒo es: "+ aÒo);
		System.out.println("El mes es: "+ mes);
		System.out.println("El dÌa es: "+ dia);
		System.out.println("El numero de la semana es: "+numeroSemanaEliminar);
		System.out.println("Fecha Actual: "+ dia + "/" + (mes) + "/" + aÒo);

		//busco la boleta de abonamiento
		String sql = "SELECT * FROM ISIS2304B301710.COMPRA_ABONAMIENTO WHERE IDBOLETA="+idBoleta;
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
					int idSitio = rs1.getInt("IDSITIO");
					// esta fecha viene asi 24/07/2017 14:00
					String fechaFunc = rs1.getString("FECHA");
					System.out.println(" ----------------->  esta es la fecha a splitiar "+fechaFunc);
					int costoBoleta = rs1.getInt("COSTOBOLETA");

					Calendar fechaFuncion = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					fechaFuncion.setTime(sdf.parse(fechaFunc));
					int numeroSemanaFuncion = fechaFuncion.get(Calendar.WEEK_OF_YEAR);

					//si cumple con la regla de 3 semanas antes de la funcion la eliminia
					if ( numeroSemanaFuncion-numeroSemanaEliminar >=3 ) 
					{
						//BORRO LA BOLETA
						String sql2 = "DELETE FROM ISIS2304B301710.COMPRA_ABONAMIENTO WHERE IDBOLETA="+idBoleta;
						PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
						prepStmt2.executeQuery();

						//Aumento las sillas del sitio con las que fueron apartadas
						String sql3 = "UPDATE ISIS2304B301710.SITIOS SET CAPACIDAD=CAPACIDAD+"+numSillas+" WHERE IDSITIO="+idSitio;
						PreparedStatement prepStmt3 = conn.prepareStatement(sql3);
						prepStmt3.executeQuery();

						//devuelvo un arreglo con [idboleta, costoBoleta , numeroSillas reservadas,se rrealizo]
						res[0]=idBoleta;
						res[1]=costoBoleta;
						res[2]=numSillas;
						res[3]=1;

						//								"___________________________________________________________________________ \n"+
						//								"La identificacion de la boleta es: "+idBoleta+"\n"+
						//								"Motivo de la devolucion de dinero: Devolucion de boleta \n"+
						//								"Por un monto de $"+(numSillas*costoBoleta) +" que corresponden a "+numSillas+" sillas reservadas. \n"+
						//								"Cordialmente Sistema FestivAndes. \n"+
						//								"___________________________________________________________________________";
					}
					else
					// La funcion es en menos o igual a 3 SEMANAS y por politicas del negocio no se puede devolver la boleta
					res[0]=idBoleta;
					res[1]=costoBoleta;
					res[2]=numSillas;
					res[3]=0;
				}
			}
		}

		return res;

	}

	public String devolverAbonamientos(int idCliente, int[] idBoletas) throws SQLException, Exception
	{
		String res = "";
		int [] datos;
		int numSillas =0;
		int CostoBoletas=0;
		double descuento= (CostoBoletas*(0.2));
		int valorDevuelto = CostoBoletas;
		int noDevueltas =0;
		for (int i = 0; i < idBoletas.length; i++) 
		{
			//devuelvo un arreglo con [idboleta, costoBoleta , numeroSillas reservadas, se realizo]
			datos = devolverAbonamiento(idCliente,idBoletas[i]);
			CostoBoletas+=datos[1];
			numSillas+=datos[2];
			
			if(datos[3]==1)
			{
				noDevueltas++;
			}

		}

		res+="Se realizo el an·lisis para la devoluciÛn de abonamientos para el cliente. \n"+
		"Los resultados fueron los siguientes: \n"+
				"_______________________________________________________________________________________________________________________________ \n"+
				"Se realizo exitosamente la devolucion de las boletas porfavor llevar estos datos impresos a la tesoreria de FestivAndes para la \n"+
				"devolucion de su dinero:\n"+
				"El cliente identificado con el numero "+ idCliente + " tramito la devoluciÛn del dinero de las boletas reservadas en el periodo\n "+
				"establecido por la empresa. La descripciÛn de la devoluciÛn es la siguiente: \n"+
				"Numero de boletas = "+idBoletas.length+"\n"+
				"Por un monto total aplicado el descuento de: $"+ valorDevuelto+"\n"+
				"Numero de sillas reservadas ="+numSillas+"\n"+
				"_______________________________________________________________________________________________________________________________ \n"+
				"\n No se pudieron devolver "+noDevueltas+" boletas ya que el evento inicia en menos de 3 semanas"+
				"\n"+
				"\n Gracias por usar nuestros servicios.";

		return res;

	}

}
