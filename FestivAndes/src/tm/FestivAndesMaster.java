/**-------------------------------------------------------------------
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación
 *
 * Materia: Sistemas Transaccionales
 * Ejercicio: VideoAndes
 * Autor: Juan Felipe García - jf.garcia268@uniandes.edu.co
 * -------------------------------------------------------------------
 */
package tm;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import dao.DAOConsultas;
import dao.DAOTablaClientes;
import dao.DAOTablaCompaniaDeTeatro;
import dao.DAOTablaCompraAbonamiento;
import dao.DAOTablaCompraBoletas;
import dao.DAOTablaEspectaculos;
import dao.DAOTablaFuncionRealizada;
import dao.DAOTablaFunciones;
import dao.DAOTablaSitios;
import dao.DAOTablaUsuarios;
import dao.DAOTablaVideos;
import vos.Video;
import vos.Cliente;
import vos.CompaniaDeTeatro;
import vos.CompraBoletas;
import vos.Espectaculo;
import vos.Funcion;
import vos.FuncionRealizada;
import vos.ListaVideos;
import vos.Sitio;
import vos.Usuario;

/**
 * Fachada en patron singleton de la aplicación
 * @author Juan
 */
public class FestivAndesMaster {


	/**
	 * Atributo estático que contiene el path relativo del archivo que tiene los datos de la conexión
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo estático que contiene el path absoluto del archivo que tiene los datos de la conexión
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * Conexión a la base de datos
	 */
	private Connection conn;


	/**
	 * Método constructor de la clase VideoAndesMaster, esta clase modela y contiene cada una de las
	 * transacciones y la logia de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto VideoAndesMaster, se inicializa el path absoluto de el archivo de conexión y se
	 * inicializa los atributos que se usan par la conexión a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public FestivAndesMaster(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/*
	 * Método que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexión a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que  retorna la conexión a la base de datos
	 * @return Connection - la conexión a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexión a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////


	/**
	 * Método que modela la transacción que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la búsqueda
	 * @throws Exception -  cualquier error que se genere durante la transacción
	 */
	public ListaVideos darVideos() throws Exception {
		ArrayList<Video> videos;
		DAOTablaVideos daoVideos = new DAOTablaVideos();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoVideos.setConn(conn);
			videos = daoVideos.darVideos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVideos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVideos(videos);
	}

	/**
	 * Método que modela la transacción que busca el/los videos en la base de datos con el nombre entra como parámetro.
	 * @param name - Nombre del video a buscar. name != null
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la búsqueda
	 * @throws Exception -  cualquier error que se genere durante la transacción
	 */
	public ListaVideos buscarVideosPorName(String name) throws Exception {
		ArrayList<Video> videos;
		DAOTablaVideos daoVideos = new DAOTablaVideos();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoVideos.setConn(conn);
			videos = daoVideos.buscarVideosPorName(name);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVideos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVideos(videos);
	}

	/**
	 * Método que modela la transacción que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como parámetro
	 * @param video - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addVideo(Video video) throws Exception {
		DAOTablaVideos daoVideos = new DAOTablaVideos();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoVideos.setConn(conn);
			daoVideos.addVideo(video);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVideos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Método que modela la transacción que agrega los videos que entran como parámetro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como parámetro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addVideos(ListaVideos videos) throws Exception {
		DAOTablaVideos daoVideos = new DAOTablaVideos();
		try
		{
			//////Transacción - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoVideos.setConn(conn);
			for(Video video : videos.getVideos())
				daoVideos.addVideo(video);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoVideos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Método que modela la transacción que actualiza el video que entra como parámetro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como parámetro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateVideo(Video video) throws Exception {
		DAOTablaVideos daoVideos = new DAOTablaVideos();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoVideos.setConn(conn);
			daoVideos.updateVideo(video);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVideos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Método que modela la transacción que elimina el video que entra como parámetro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como parámetro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteVideo(Video video) throws Exception {
		DAOTablaVideos daoVideos = new DAOTablaVideos();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoVideos.setConn(conn);
			daoVideos.deleteVideo(video);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVideos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Método que modela la transacción que retorna el/los videos mas alquilados
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la búsqueda
	 * @throws Exception -  cualquier error que se genere durante la transacción
	 */
	public ListaVideos videosMasAlquilados() throws Exception {
		ArrayList<Video> videos;
		DAOTablaVideos daoVideos = new DAOTablaVideos();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoVideos.setConn(conn);
			videos = daoVideos.darVideoMasAlquilado();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVideos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVideos(videos);
	}


	//////////////////////////////////////////SERVICIOS DEL SISTEMA///////////////////////////////////////////////////////////////////////////////

	public void crearUsuario(Usuario usuario) throws Exception
	{
		DAOTablaUsuarios daoUsuarios= new DAOTablaUsuarios();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoUsuarios.setConn(conn);
			daoUsuarios.crearUsuario(usuario);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoUsuarios.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	////////////////////////// SERVICIOS DE UN ADMINISTRADOR //////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Metodo que modela la transaccion que agrega una sola compania la base de datos.
	 * <b> post: </b> se ha agregado la compania que entra como parametro
	 * @param compania - la compania a agregar. compania != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addCompania(CompaniaDeTeatro compania) throws Exception {
		DAOTablaCompaniaDeTeatro daoCompania = new DAOTablaCompaniaDeTeatro();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoCompania.setConn(conn);
			daoCompania.addCompania(compania);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCompania.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que agrega una solo cliente la base de datos.
	 * <b> post: </b> se ha agregado un cliente que entra como parametro
	 * @param cliente - el cliente a agregar. cliente != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addCliente(Cliente cliente) throws Exception {
		DAOTablaClientes daoClientes = new DAOTablaClientes();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoClientes.setConn(conn);
			daoClientes.addCliente(cliente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que agrega una solo espectaculo la base de datos.
	 * <b> post: </b> se ha agregado un espectaculo que entra como parametro
	 * @param espectaculo - el espectaculo a agregar. espectaculo != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addEspectaculo(Espectaculo espectaculo) throws Exception {
		DAOTablaEspectaculos daoEspectaculo = new DAOTablaEspectaculos();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoEspectaculo.setConn(conn);
			daoEspectaculo.addEspectaculo(espectaculo);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoEspectaculo.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que agrega una solo espectaculo la base de datos.
	 * <b> post: </b> se ha agregado un espectaculo que entra como parametro
	 * @param espectaculo - el espectaculo a agregar. espectaculo != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addSitio(Sitio sitio) throws Exception {
		DAOTablaSitios daoSitio = new DAOTablaSitios();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoSitio.setConn(conn);
			daoSitio.addSitio(sitio);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoSitio.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * Metodo que modela la transaccion que agrega una solo espectaculo la base de datos.
	 * <b> post: </b> se ha agregado un espectaculo que entra como parametro
	 * @param espectaculo - el espectaculo a agregar. espectaculo != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addFuncion(Funcion funcion) throws Exception {
		DAOTablaFunciones daoFuncion= new DAOTablaFunciones();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoFuncion.setConn(conn);
			daoFuncion.addFuncion(funcion);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoFuncion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}






	//////////////////////////SERVICIOS DE UN CLIENTE O NO CLIENTE //////////////////////////////////////////////////////////////////////////////////////////////
	public void addModPreferenciaCliente(int id, String prefCategoria, String prefSitio) throws Exception
	{
		DAOTablaClientes daoCliente = new DAOTablaClientes();
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoCliente.setConn(conn);
			daoCliente.addModPreferenciaCliente( id,  prefCategoria, prefSitio);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCliente.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}


	}

	public void addComprarBoletas(CompraBoletas comBoletas) throws Exception {

		DAOTablaCompraBoletas daoCompraBoleta= new DAOTablaCompraBoletas();

		try
		{
			//////Transacción
			this.conn = darConexion();
			daoCompraBoleta.setConn(conn);
			daoCompraBoleta.addComprarBoleta(comBoletas);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {

				daoCompraBoleta.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	public Funcion buscarFuncionPorId(int id) throws Exception
	{
		DAOTablaFunciones daoFuncion = new DAOTablaFunciones();
		Funcion resFuncion = new Funcion(0, 0, 0, null, null, 0,0);
		try
		{
			//////Transacción
			this.conn = darConexion();
			daoFuncion.setConn(conn);
			resFuncion = daoFuncion.darFuncionPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoFuncion.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return resFuncion;
	}


	//////////////////////////SERVICIOS DE UN OPERARIO //////////////////////////////////////////////////////////////////////////////////////////////

	public void addRegRealizacionFuncion(FuncionRealizada funReali) throws Exception {

		DAOTablaFuncionRealizada daoFunRealizada = new DAOTablaFuncionRealizada();

		try
		{
			//////Transaccion
			this.conn = darConexion();
			daoFunRealizada.setConn(conn);
			daoFunRealizada.addComprarBoleta(funReali);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {

				daoFunRealizada.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


////////////////////////////////SERVICIOS DE LAS CONSULTAS///////////////////////////////////////////////////////////////////////////////////////////////////////
	public String reporteDeUnaFuncion(int idFuncion) throws Exception
	{
		DAOConsultas daoConsulta = new DAOConsultas();
		String res=null;
		try
		{
			//////Transaccion
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoConsulta.setConn(conn);
			res = daoConsulta.reporteDeUnaFuncion(idFuncion);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {

				daoConsulta.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 *
	 * @param idEspectaculo
	 * @return
	 * @throws Exception
	 */
	public String reporteDeUnEspectaculo(int idEspectaculo) throws Exception
	{
		DAOConsultas daoConsulta = new DAOConsultas();
		String res=null;
		try
		{
			//////Transaccion
			this.conn = darConexion();
			daoConsulta.setConn(conn);
			res = daoConsulta.reporteDeUnEspectaculo(idEspectaculo);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {

				daoConsulta.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * modela la transaccion de un reporte de un sitio
	 * @param idSitio
	 * @return
	 * @throws Exception
	 */
	public String reporteDeUnSitio(int idSitio) throws Exception
	{
		DAOConsultas daoConsulta = new DAOConsultas();
		String res=null;
		try
		{
			//////Transaccion
			this.conn = darConexion();
			daoConsulta.setConn(conn);
			res = daoConsulta.reporteDeUnSitio(idSitio);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {

				daoConsulta.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public String consultarFuncionesEspectaculosProgramados(String xCategoria, String xIdioma, int serTra, String fechaInicio, String fechaFinal) throws Exception
	{
		DAOConsultas daoConsulta = new DAOConsultas();
		String res=null;
		try
		{
			//////Transaccion
			this.conn = darConexion();
			daoConsulta.setConn(conn);
			res = daoConsulta.consultarFuncionesEspectaculosProgramados(xCategoria,xIdioma,serTra,fechaInicio,fechaFinal);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {

				daoConsulta.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	public String bono2(String f1, String f2) throws Exception
	{
		DAOConsultas daoConsulta = new DAOConsultas();
		String res=null;
		try
		{
			//////Transaccion
			this.conn = darConexion();
			daoConsulta.setConn(conn);
			res = daoConsulta.bono2(f1,f2);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {

				daoConsulta.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * RF10 V2.0
	 * @param boletas
	 * @return
	 * @throws Exception
	 */
	public String regCompraMultiBoleta(CompraBoletas[]  boletas) throws Exception
	{
		DAOTablaCompraBoletas daoCompraBol = new DAOTablaCompraBoletas();
		String res=null;
		try
		{
			//------> inicio la transaccion
			this.conn = darConexion();

			//desactivo el autocommit
			conn.setAutoCommit(false);

			//agrego nivel de aislamiento a la transaccion
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);

			daoCompraBol.setConn(conn);
			res = daoCompraBol.regCompraMultiBoleta(boletas);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error con sql
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error de negocio.
			conn.rollback();
			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoCompraBol.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * RF12
	 *
	 * @param idBoleta
	 * @return
	 * @throws Exception
	 */
	public String devolverBoleta(int idBoleta, int idCliente) throws Exception
	{
		DAOTablaCompraBoletas daoCompraBol = new DAOTablaCompraBoletas();
		String res=null;
		try
		{
			//------> inicio la transaccion
			this.conn = darConexion();

			//desactivo el autocommit
			conn.setAutoCommit(false);

			//agrego nivel de aislamiento a la transaccion
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);

			daoCompraBol.setConn(conn);
			res = daoCompraBol.devolverBoleta(idBoleta, idCliente);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error con sql
			conn.rollback();

			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error de negocio.
			conn.rollback();

			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoCompraBol.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * RF11 V2.0
	 * @param boletas
	 * @return
	 * @throws Exception
	 */
	public String regAbonamiento(CompraBoletas[] boletas) throws Exception
	{
		DAOTablaCompraAbonamiento daoCompraAbonamiento = new DAOTablaCompraAbonamiento();
		String res=null;
		try
		{
			//-----> inicio la transaccion
			this.conn = darConexion();

			//desactivo el autocommit
			conn.setAutoCommit(false);

			//agrego nivel de aislamiento a la transaccion
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);

			daoCompraAbonamiento.setConn(conn);
			res = daoCompraAbonamiento.regAbonamiento(boletas);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error con sql
			conn.rollback();

			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error de negocio.
			conn.rollback();

			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoCompraAbonamiento.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * RF13 v2.0
	 *
	 * @param idCliente
	 * @param arrayBoletasDevueltas
	 * @return
	 * @throws Exception
	 */
	public String devolverAbonamiento(int idCliente, int[] arrayBoletasDevueltas) throws Exception
	{
		DAOTablaCompraAbonamiento daoCompraAbona = new DAOTablaCompraAbonamiento();
		String res=null;
		try
		{
			//----> inicio la transaccion
			this.conn = darConexion();

			//desactivo el autocommit
			conn.setAutoCommit(false);

			//agrego nivel de aislamiento a la transaccion
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);

			daoCompraAbona.setConn(conn);
			res = daoCompraAbona.devolverAbonamientos(idCliente,arrayBoletasDevueltas);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error con sql
			conn.rollback();

			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error de negocio.
			conn.rollback();

			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoCompraAbona.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	/**
	 * RFC8 v2.0   ojo se llama desde consultaservices
	 * @param idCompania
	 * @return
	 * @throws Exception
	 */
	public String consultarCompania(int idCompania) throws Exception
	{
		DAOConsultas daoConsultas = new DAOConsultas();
		String res=null;
		try
		{
			//----> inicio la transaccion
			this.conn = darConexion();

			//desactivo el autocommit
			conn.setAutoCommit(false);

			//agrego nivel de aislamiento a la transaccion
			conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);

			daoConsultas.setConn(conn);
			res = daoConsultas.consultarCompania(idCompania);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error con sql
			conn.rollback();

			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error de negocio.
			conn.rollback();

			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoConsultas.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	/**
	 * RF14 v2.0
	 *
	 * @param idFuncion
	 * @return
	 * @throws Exception
	 */
	public String cancelarFuncion(int idFuncion) throws Exception
	{
		DAOTablaCompraBoletas daoCompraBol = new DAOTablaCompraBoletas();
		String res=null;
		try
		{
			//----> inicio la transaccion
			this.conn = darConexion();

			//desactivo el autocommit
			conn.setAutoCommit(false);

			//agrego nivel de aislamiento a la transaccion
			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);

			daoCompraBol.setConn(conn);
			res = daoCompraBol.cancelarFuncion(idFuncion);
			conn.commit();

			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error con sql
			conn.rollback();

			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error de negocio.
			conn.rollback();

			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoCompraBol.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	/**
	 * RFC 7
	 *
	 * @param idCliente
	 * @return
	 */
	public String consultarAsistenciaCliente(int idCliente) throws Exception {
		DAOTablaCompraBoletas daoCompraBol = new DAOTablaCompraBoletas();
		String res=null;
		try
		{
			//----> inicio la transaccion
			this.conn = darConexion();

			//activo el autocommit
			conn.setAutoCommit(true);

			daoCompraBol.setConn(conn);
			res = daoCompraBol.consultarAsistenciaCliente(idCliente);
			conn.commit();

			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoCompraBol.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	/**
	 * RFC9 v1
	 */
	public String consultarAsistencia(int idCompania, String xFechaInicio, String xFechaFinal, String xorderBy) throws Exception
	{
		DAOConsultas daoConsultas = new DAOConsultas();
		String res=null;
		try
		{
			//----> inicio la transaccion
			this.conn = darConexion();

			//desactivo el autocommit
			conn.setAutoCommit(false);

			//agrego nivel de aislamiento a la transaccion
			conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);

			daoConsultas.setConn(conn);
			res = daoConsultas.consultarAsistencia(idCompania,xFechaInicio,xFechaFinal,xorderBy);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error con sql
			conn.rollback();

			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error de negocio.
			conn.rollback();

			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoConsultas.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	/**
	 * RFC9 v2
	 */
	public String consultarInasistencia(int idCompania, String xFechaInicio, String xFechaFinal,  String xorderBy) throws Exception
	{
		DAOConsultas daoConsultas = new DAOConsultas();
		String res=null;
		try
		{
			//----> inicio la transaccion
			this.conn = darConexion();

			//desactivo el autocommit
			conn.setAutoCommit(false);

			//agrego nivel de aislamiento a la transaccion
			conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);

			daoConsultas.setConn(conn);
			res = daoConsultas.consultarInasistencia(idCompania,xFechaInicio,xFechaFinal,xorderBy);
			conn.commit();
			return res;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error con sql
			conn.rollback();

			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();

			//rollback si hay error de negocio.
			conn.rollback();

			throw e;
		} finally {
			try {

				//activo el autocommit de nuevo
				conn.setAutoCommit(true);

				daoConsultas.cerrarRecursos();

				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

}
