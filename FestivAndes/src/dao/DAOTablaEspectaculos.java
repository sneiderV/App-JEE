package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Espectaculo;

public class DAOTablaEspectaculos {
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
	public DAOTablaEspectaculos() 
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
	 * Metodo que agrega el espectaculo que entra como parametro a la base de datos.
	 * @param espectaculo - el espectaculo a agregar. espectaculo !=  null
	 * <b> post: </b> se ha agregado el espectaculo a la base de datos en la transaction actual. pendiente que el festivandes master
	 * haga commit para que el espectaculo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el video a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addEspectaculo(Espectaculo compania) throws SQLException, Exception {

		//String sql = "INSERT INTO ISIS2304B301710.ESPECTACULOS VALUES (";
		String sql = "INSERT INTO ISIS2304B301710.ESPECTACULOS VALUES (";
		sql += compania.getIdEspec() + ",'";
		sql += compania.getNombre() + "','";
		sql += compania.getDescrip()+ "','";
		sql += compania.getCategoria()+ "',";
		sql += compania.getDuracion()+ ",'";
		sql += compania.getIdioma()+ "',";
		sql += compania.getCostoRealizacion()+ ",'";
		sql += compania.getRequeri_tec()+ "',";
		sql += compania.getServ_tradu() + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();

	}
}
