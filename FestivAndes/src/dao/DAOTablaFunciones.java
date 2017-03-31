package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import vos.Funcion;

public class DAOTablaFunciones 
{
	
	Format formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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
	public DAOTablaFunciones() 
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
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Metodo que agrega la compania de teatro que entra como parametro a la base de datos.
	 * @param compania - la compania a agregar. compania !=  null
	 * <b> post: </b> se ha agregado la compania a la base de datos en la transaction actual. pendiente que el festivandes master
	 * haga commit para que la compania baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addFuncion(Funcion funcion) throws SQLException, Exception {

		//String sql = "INSERT INTO ISIS2304B301710.COMPANIA_TEATRO VALUES (";
		String sql = "INSERT INTO ISIS2304B301710.FUNCIONES VALUES (";
		sql += funcion.getIdFuncion() + ",";
		sql += funcion.getIdSitio() + ",";
		sql += funcion.getIdEspectaculo() + ",'";
		sql += funcion.getNombre() + "','";
		sql += funcion.getFecha() + "',";
		sql += funcion.getDuracion() + ",";
		sql += funcion.getCostoBoleta()+")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
	
	/**
	 * retorna la funcion pasada por id
	 * @param idFuncion
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public Funcion darFuncionPorId (int idFuncion) throws SQLException, Exception {

		Funcion funcion = new Funcion(0, 0, 0, null, null, 0,0);

		String sql = "SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDFUNCION="+idFuncion;
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next())
		{
			int idFunc = Integer.parseInt(rs.getString("IDFUNCION"));
			int idSitio = Integer.parseInt(rs.getString("IDSITIO"));
			int idEspectaculo = Integer.parseInt(rs.getString("IDESPECTACULO"));
			String nombre = rs.getString("NOMBRE");
			String fecha = rs.getString("FECHA");
			int duracion = Integer.parseInt(rs.getString("DURACION"));
			int costoBoleta = rs.getInt("COSTOBOLETA");
			
			funcion = new Funcion(idFunc, idSitio, idEspectaculo, nombre, fecha, duracion,costoBoleta);
		}
		
		return funcion;
	}
	
	public int darIdSitiodeFuncion(int idFuncion) throws SQLException, Exception {
		
		int idFunc = 0 ;
		//String sql = "INSERT INTO ISIS2304B301710.ESPECTACULOS VALUES (";
		String sql = "SELECT IDSITIO FROM ISIS2304B301710.FUNCIONES WHERE IDFUNCION ="+idFuncion;
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		ResultSet rs = prepStmt.executeQuery();
		
		while(rs.next())
		{
			idFunc = Integer.parseInt(rs.getString("IDSITIO"));
		}
		
		return idFunc;
	}




}
