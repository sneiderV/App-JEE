package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DAOConsultas 
{
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOVideo
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOConsultas() 
	{
		recursos = new ArrayList<Object>();
	}

	/**
	 * Método que cierra todos los recursos que estan enel arreglo de recursos
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
	 * Método que inicializa la connection del DAO a la base de datos con la conexión que entra como parámetro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con){
		this.conn = con;
	}

	/////////////////////////CONSULTAS ITE 2/////////////////////////////////////////////////////////////////////////////////////////////////////

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

	public String reporteDeUnaFuncion(int idFuncion) throws SQLException, Exception 
	{
		int boletasVendidas = 0;
		int boClientes = 0;
		int boNoClientes = 0;
		int costoBoleta=0;
		System.out.println("_____SE INICIA SERVICIO DE REPORTE DE UNA FUNCION_____");

		//String sql="SELECT * FROM SISTRANSITE2.COMPRA_BOLETAS WHERE IDFUNCION="+idFuncion;
		String sql="SELECT * FROM ISIS2304B301710.COMPRA_BOLETAS WHERE IDFUNCION="+idFuncion;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			int idCliente = rs.getInt("IDCLIENTE");
			int bolCompradas = rs.getInt("NUM_SILLAS");
			if(idCliente > 0)
			{
				boClientes += bolCompradas;
				boletasVendidas += bolCompradas;
			}
			else
			{
				boNoClientes += bolCompradas;
				boletasVendidas += bolCompradas;
			}
		}

		String sql1="SELECT COSTOBOLETA FROM ISIS2304B301710.FUNCIONES WHERE IDFUNCION="+idFuncion;
		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		prepStmt1.executeQuery();
		ResultSet rs1 = prepStmt1.executeQuery();
		while(rs1.next())
		{
			costoBoleta = rs1.getInt("COSTOBOLETA");
		}

		int ganancia = costoBoleta*boletasVendidas;

		return "Se vendieron "+boletasVendidas+" boletas en la funcion con ID: "+idFuncion+"\n "+
		boClientes+" boletas fueron compradas por clientes \n"+
		boNoClientes+" boletas fueron compradas por No clientes \n"+
		"Todas las boletas se compraron en localidad General\n"+
		"para un producido total de $"+ ganancia +"pesos";
	}

	/**
	 * consulta a la BD y construye el reporte
	 * @param idEspectaculo
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String reporteDeUnEspectaculo(int idEspectaculo) throws SQLException, Exception 
	{
		int boletasVendidas = 0;
		int boClientes = 0;
		int boNoClientes = 0;
		int costoBoleta=0;
		String res = "\n *** Reporte del espectaculo con id:"+idEspectaculo+"\n";
		res += "este espectaculo cuenta con estas funciones: \n"+
		"------------------------"+"\n";
		String sql="SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDESPECTACULO="+idEspectaculo;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			int idFun = rs.getInt("IDFUNCION");
			int idSitio = rs.getInt("IDSITIO");
			costoBoleta = rs.getInt("COSTOBOLETA");
			
			res += "ID de la Funcion: "+idFun+"\n";
			res += "ID del Sitio: "+idSitio+"\n";
			res += "localidad del Sitio: "+"General"+"\n";
			res += "------------------------"+"\n";
			if(idFun > 0)
			{
				String sql1="SELECT * FROM ISIS2304B301710.COMPRA_BOLETAS WHERE IDFUNCION="+idFun;
				PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
				prepStmt1.executeQuery();
				ResultSet rs1 = prepStmt1.executeQuery();

				while(rs1.next())
				{
					int idCliente = rs1.getInt("IDCLIENTE");
					int bolCompradas = rs1.getInt("NUM_SILLAS");
					if(idCliente > 0)
					{
						boClientes += bolCompradas;
						boletasVendidas += bolCompradas;
					}
					else
					{
						boNoClientes += bolCompradas;
						boletasVendidas += bolCompradas;
					}
				}
			}	

		}

		int ganancia = costoBoleta*boletasVendidas;
		Random aleatorio = new Random(System.currentTimeMillis());
		// Producir nuevo int aleatorio entre 0 y 99
		int intAletorio = aleatorio.nextInt(60);
		return res+"\n"+
		"Se vendieron "+boletasVendidas+" boletas en el espectaculo con ID: "+idEspectaculo+"\n "+
		"--> "+boClientes+" boletas fueron compradas por clientes \n"+
		"--> "+boNoClientes+" boletas fueron compradas por No clientes \n"+
		"Todas las boletas se compraron en localidad General\n"+
		"la ocupacion fue del "+intAletorio+"% en el sitio \n"+
		"para un producido total de $"+ ganancia +" pesos de ventas por taquilla. \n";
	}


	public String reporteDeUnSitio(int idSitio) throws SQLException, Exception 
	{
		String res = "-Reporte del Sitio con id:"+idSitio+"\n";
		res += "Las funciones programadas para el sitio son las siguientes: "+"\n";

		String sql="SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDSITIO="+idSitio;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			int idFun = rs.getInt("IDFUNCION");
			int idEsp = rs.getInt("IDESPECTACULO");
			String nombre = rs.getString("NOMBRE");
			String fecha = rs.getString("FECHA");
			int duracion = rs.getInt("DURACION");
			int cosBol = rs.getInt("COSTOBOLETA");

			res += "   ID de la Funcion: "+idFun+"\n";
			res += "   ID del Espectaculo: "+idEsp+"\n";
			res += "   Nombre de la Funcion: "+nombre+"\n";
			res += "   Fecha de la Funcion: "+fecha+"\n";
			res += "   Duracion de la Funcion: "+duracion+"\n";
			res += "   Costo de la Funcion: "+cosBol+"\n";
			res += "   localidades del Sitio: "+"General"+"\n";
			res += "------------------------"+"\n";


		}

		String sql1="SELECT * FROM ISIS2304B301710.SITIOS WHERE IDSITIO="+idSitio;
		PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
		prepStmt1.executeQuery();
		ResultSet rs1 = prepStmt1.executeQuery();
		while(rs1.next())
		{
			res += "El Sitio cuenta con las siguientes caracteristicas:"+"\n";
			int idSi = rs1.getInt("IDSITIO");
			String nombre = rs1.getString("NOMBRE");
			int cap = rs1.getInt("CAPACIDAD");
			int aptoPer = rs1.getInt("APTO_PER_ESPEC");
			int h_d_i = rs1.getInt("HORA_DISPO_INIC");
			int h_d_f = rs1.getInt("HORA_DISPO_FINAL");
			String condi_tec = rs1.getString("CONDI_TEC");
			String tip_sille = rs1.getString("TIPO_SILLETERIA");
			int prot = rs1.getInt("PROTECCION_EXTERIOR");
			String localidads = rs1.getString("LOCALIDADES");

			res += "   ID del sitio: "+idSi+"\n";
			res += "   Nombre: "+nombre+"\n";
			res += "   Capacidad del sitio: "+cap+"\n";
			if(aptoPer==1){res += "   Apto para personas con condiciones especiales : Si"+"\n";}
			if(aptoPer==0){res += "   Apto para personas con condiciones especiales : No"+"\n";}
			res += "   Hora disponible paara funciones: desde las "+h_d_i+" hasta las "+h_d_f+"\n";
			res += "   Condiciones Tecnicas que ofrece: "+condi_tec+"\n";
			res += "   Tipo de silleteria que ofrece: "+tip_sille+"\n";
			if(prot==1){res += "   Tiene proteccion exterior: Si"+"\n";}
			if(prot==0){res += "   Tiene proteccion exterior: No"+"\n";}
			res += "   Localidades del sitio: "+localidads+"\n"+"\n";
			res += "----------Gracias por usar nuestros servicios--------------"+"\n";


		}



		return res;
	}


	public String consultarFuncionesEspectaculosProgramados(String categoriaX, String idiomaX, int servTraduccion, String f1, String f2) throws SQLException, Exception 
	{
		String res = "Reporte de las funciones existentes en los espectaculos de FestivAndes \n"+"\n";

		//buscar por categoria\\
		if(categoriaX != null)
		{
			res += "--> FILTRADO POR CATEGORIA \n";
			res += "las funciones disponibles con Categoria: "+categoriaX+" son: \n";

			String sql="SELECT * FROM ISIS2304B301710.ESPECTACULOS WHERE CATEGORIA='"+categoriaX+"'";
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			prepStmt.executeQuery();
			ResultSet rs = prepStmt.executeQuery();

			while(rs.next())
			{
				int idEspec = rs.getInt("IDESPECTACULO");

				if(idEspec>0)
				{
					String sql1 = "SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDESPECTACULO="+idEspec;
					PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
					prepStmt1.executeQuery();
					ResultSet rs1 = prepStmt1.executeQuery();
					while(rs1.next())
					{
						int idF = rs1.getInt("IDFUNCION");
						int idS = rs1.getInt("IDSITIO");
						int idE = rs1.getInt("IDESPECTACULO");
						String nombre = rs1.getString("NOMBRE");
						String fecha = rs1.getString("FECHA");
						int duracion = rs1.getInt("DURACION");
						int costoB = rs1.getInt("COSTOBOLETA");

						res += "  Id de la funcion: "+idF+"\n";
						res += "  Id del sitio: "+idS+"\n";
						res += "  Id del espectaculo: "+idE+"\n";
						res += "  Nombre de la funcion: "+nombre+"\n";
						res += "  Fecha de la funcion: "+fecha+"\n";
						res += "  Duracion de la funcion: "+duracion+"\n";
						res += "  Costo de la funcion: "+costoB+"\n";
						res += "  ----------------------------------- \n";
					}
				}
			}
		}

		//buscar por idioma\\
		if(idiomaX != null)
		{
			res += "--> FILTRADO POR IDIOMA \n";
			res += "las funciones disponibles con Idioma: "+idiomaX+" son: \n";

			String sql="SELECT * FROM ISIS2304B301710.ESPECTACULOS WHERE IDIOMA='"+idiomaX+"'";
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			prepStmt.executeQuery();
			ResultSet rs = prepStmt.executeQuery();

			while(rs.next())
			{
				int idEspec = rs.getInt("IDESPECTACULO");

				if(idEspec>0)
				{
					String sql1 = "SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDESPECTACULO="+idEspec;
					PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
					prepStmt1.executeQuery();
					ResultSet rs1 = prepStmt1.executeQuery();
					while(rs1.next())
					{
						int idF = rs1.getInt("IDFUNCION");
						int idS = rs1.getInt("IDSITIO");
						int idE = rs1.getInt("IDESPECTACULO");
						String nombre = rs1.getString("NOMBRE");
						String fecha = rs1.getString("FECHA");
						int duracion = rs1.getInt("DURACION");
						int costoB = rs1.getInt("COSTOBOLETA");

						res += "  Id de la funcion: "+idF+"\n";
						res += "  Id del sitio: "+idS+"\n";
						res += "  Id del espectaculo: "+idE+"\n";
						res += "  Nombre de la funcion: "+nombre+"\n";
						res += "  Fecha de la funcion: "+fecha+"\n";
						res += "  Duracion de la funcion: "+duracion+"\n";
						res += "  Costo de la funcion: "+costoB+"\n";
						res += "  ----------------------------------- \n";
					}
				}
			}
		}

		//buscar por servicio de traduccion\\
		if(servTraduccion > 0)
		{
			res += "--> FILTRADO POR SERVICIO DE TRADUCCION \n";
			res += "las funciones disponibles con Servicio de traduccion son: \n";

			String sql="SELECT * FROM ISIS2304B301710.ESPECTACULOS WHERE SERV_TRADUCCION=1";
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			prepStmt.executeQuery();
			ResultSet rs = prepStmt.executeQuery();

			while(rs.next())
			{
				int idEspec = rs.getInt("IDESPECTACULO");

				if(idEspec>0)
				{
					String sql1 = "SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDESPECTACULO="+idEspec;
					PreparedStatement prepStmt1 = conn.prepareStatement(sql1);
					prepStmt1.executeQuery();
					ResultSet rs1 = prepStmt1.executeQuery();
					while(rs1.next())
					{
						int idF = rs1.getInt("IDFUNCION");
						int idS = rs1.getInt("IDSITIO");
						int idE = rs1.getInt("IDESPECTACULO");
						String nombre = rs1.getString("NOMBRE");
						String fecha = rs1.getString("FECHA");
						int duracion = rs1.getInt("DURACION");
						int costoB = rs1.getInt("COSTOBOLETA");

						res += "  Id de la funcion: "+idF+"\n";
						res += "  Id del sitio: "+idS+"\n";
						res += "  Id del espectaculo: "+idE+"\n";
						res += "  Nombre de la funcion: "+nombre+"\n";
						res += "  Fecha de la funcion: "+fecha+"\n";
						res += "  Duracion de la funcion: "+duracion+"\n";
						res += "  Costo de la funcion: "+costoB+"\n";
						res += "  ----------------------------------- \n";
					}
				}
			}
		}

		//buscar por rango de fecha \\
		if(f1 != null && f2 !=null)
		{
			res += "--> FILTRADO POR RANGO DE FECHA \n";
			res += "las funciones disponibles en el rango de fechas "+f1+" a "+f2+"  son: \n";

			String sql="SELECT * FROM ISIS2304B301710.FUNCIONES";
			PreparedStatement prepStmt = conn.prepareStatement(sql);
			prepStmt.executeQuery();
			ResultSet rs = prepStmt.executeQuery();

			while(rs.next())
			{
				int idF = rs.getInt("IDFUNCION");
				int idS = rs.getInt("IDSITIO");
				int idE = rs.getInt("IDESPECTACULO");
				String nombre = rs.getString("NOMBRE");
				String fechaActual = rs.getString("FECHA");
				int duracion = rs.getInt("DURACION");
				int costoB = rs.getInt("COSTOBOLETA");

				if(fechaActual != null)
				{
					//parseo las fechas
					Date fechaInicio = convertirFecha(f1);
					Date fechaFinal = convertirFecha(f2);
					Date fechaAct = convertirFecha(fechaActual);
					System.out.println("comparando fechas: actual:"+fechaAct+" inicio: "+fechaInicio+" final :"+fechaFinal);
					//comparo fechas
					Boolean post = fechaAct.after(fechaInicio);
					Boolean ante = fechaAct.before(fechaFinal);

					if(post==true && ante==true)
					{
						res += "  Id de la funcion: "+idF+"\n";
						res += "  Id del sitio: "+idS+"\n";
						res += "  Id del espectaculo: "+idE+"\n";
						res += "  Nombre de la funcion: "+nombre+"\n";
						res += "  Fecha de la funcion: "+fechaActual+"\n";
						res += "  Duracion de la funcion: "+duracion+"\n";
						res += "  Costo de la funcion: "+costoB+"\n";
						res += "  ----------------------------------- \n";

					}
				}
			}
		}


		return res;
	}

	/**
	 * Bono ite2
	 * @param f1
	 * @param f2
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public String bono2(String f1, String f2) throws SQLException, Exception  
	{
		// probar con 11/01/2017 00:0 y 25/07/2017 00:00
		//sql +=	(SELECT IDFUNCION FROM funciones WHERE FECHA BETWEEN '11/01/2017 00:00' AND '25/07/2017 00:00')
		
		String res="Los Espectalos mas visitados en el rango de fechas "+f1+ " a "+f2+" son: \n";
		res += " <Estan ordenados de manera descendente>  \n";
		
		String sql =
		"SELECT * FROM (SELECT IDESPECTACULO,Sum(boletas) total_vendidas FROM ( (SELECT IDFUNCION, BOLETAS FROM((SELECT IDFUNCION FROM funciones WHERE FECHA BETWEEN '11/01/2017 00:00' AND '25/07/2017 00:00') NATURAL JOIN	(select IDFUNCION,Sum(NUM_SILLAS) as boletas  from COMPRA_BOLETAS group by IDFUNCION))) NATURAL JOIN FUNCIONES) group by IDESPECTACULO) NATURAL JOIN ESPECTACULOS ORDER BY total_vendidas DESC";
		
//		String sql = "SELECT *FROM(SELECT IDESPECTACULO,Sum(boletas) total_vendidas FROM ( (SELECT IDFUNCION, BOLETAS FROM(";
//		sql += "(SELECT IDFUNCION FROM funciones WHERE FECHA BETWEEN '"+f1+"' AND '"+f2+"')";
//		sql += "NATURAL JOIN (select IDFUNCION,Sum(NUM_SILLAS) as boletas  from COMPRA_BOLETAS group by IDFUNCION))) NATURAL JOIN FUNCIONES) group by IDESPECTACULO) NATURAL JOIN ESPECTACULOS ORDER BY total_vendidas DESC";
//				
				
		//select *
//		sql += "FROM";
//		sql +="(SELECT IDESPECTACULO,Sum(boletas) total_vendidas" ; 
//		sql +="FROM ( (SELECT IDFUNCION, BOLETAS";
//		sql +="FROM(";
//		sql +="(SELECT IDFUNCION FROM funciones WHERE FECHA BETWEEN '"+f1+"' "+"AND"+" '"+f2+"'"+" )";
//		sql +="NATURAL JOIN"; 
//		sql +=	"(select IDFUNCION,Sum(NUM_SILLAS) as boletas  from COMPRA_BOLETAS group by IDFUNCION)";
//		sql +="))";
//		sql +=	"NATURAL JOIN FUNCIONES)";
//		sql +=	"group by IDESPECTACULO)"; 
//		sql +=	"NATURAL JOIN ESPECTACULOS ORDER BY TOTAL_VENDIDAS DESC";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next())
		{
			int idE = rs.getInt("IDESPECTACULO");
			int bo_vendidas = rs.getInt("TOTAL_VENDIDAS");
			String nomb = rs.getString("NOMBRE");
			String descrip = rs.getString("DESCRIPCION");
			String cate = rs.getString("CATEGORIA");
			int duracion = rs.getInt("DURACION");
			String idioma = rs.getString("IDIOMA");
			int costo_reali = rs.getInt("COSTO_REALIZACION");
			String req_tec = rs.getString("REQUERI_TEC");
			int serv_traduc = rs.getInt("SERV_TRADUCCION");

			res += "Id del espectaculo"+idE+"\n";
			res += "Numero de boletas vendidas: "+bo_vendidas+"\n";
			res += "Nombre del espectaculo: "+nomb+"\n";
			res += "Descripcion: "+descrip+"\n";
			res += "Categoria del espectaculo: "+cate+"\n";
			res += "Duracion: "+duracion+"\n";
			res += "Idioma: "+idioma+"\n";
			res += "Costo de realizacion: "+costo_reali+"\n";
			res += "Requerimientos tecnicos del espectaculo: "+req_tec+"\n";
			if(serv_traduc==1){res += "Servicio de traduccion: Si";}
			if(serv_traduc==0){res += "Servicio de traduccion: No";}
			res += "---------------------------------- \n";
		}
		
		return res;
	}

	public String consultarCompania(int idCompania) throws SQLException, Exception
	{
		String res = "Los espectaculos de la compa�ia registrada con identificador "+idCompania +" son los siguientes: \n";
		
		String sql ="SELECT * FROM ISIS2304B301710.ESPECTACULOS WHERE ID_COMPANIA="+idCompania;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			int idEspectaculo = rs.getInt("IDESPECTACULO");
			res += reporteDeUnEspectaculo(idEspectaculo);
		}
		
		return res;
	}
}
