package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.CompraBoletas;

public class DAOTablaCompraBoletas 
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
	public DAOTablaCompraBoletas() 
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
	public void addComprarBoleta(CompraBoletas compraBoleta) throws SQLException, Exception 
	{
System.out.println("_____SE INICIA SERVICIO DE COMPRA DE BOLETAS_____");
		
		String sql = "SELECT * FROM ISIS2304B301710.FUNCIONES WHERE IDFUNCION="+compraBoleta.getIdFuncion();
		
System.out.println("buscar� funci�n con id:"+compraBoleta.getIdFuncion());

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
								
						System.out.println("---> Se comprar�n: "+compraBoleta.getNum_sillas()+" boletas");
					}
					
					else
						System.out.println("--->No hay suficientes entradas");							
				}
			}
		}
	}
	
	
}
