package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import tm.FestivAndesMaster;

/**
 * rest de las consultas que se pueden realizar al sistema
 * @author SneiderV
 *
 */

@Path("consultaServ")
public class ConsultaServices 
{
	/**
	 * Atributo que usa la anotación @Context para tener el ServletContext de la conexión actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Método que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}


	//	private String doErrorMessage(Exception e){
	//		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	//	}

	// METODO USADO PARA PROBAR EL URI CORRECTO \\
	@GET
	@Path("sayHello")
	public String sayHello(@QueryParam("name")String name) 
	{
		System.out.println("aasaaa");
		return "asa: "+name;
	}
	//////////////////////////////SERVICIOS DE CONSULTA////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GET
	@Path("reporteFuncion")
	public String reporteDeUnaFuncion(@QueryParam("idFuncion") int idFuncion)
	{
		System.out.println("_______GENERANDO REPORDE DE UNA FUNCION______");
		System.out.println("--->bucar la funcion con id:"+idFuncion);
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		String res;
		try {
			res = tm.reporteDeUnaFuncion(idFuncion);
		} catch (Exception e) {
			return "Error en la consulta del Query";
		}
		return res;

	}

	@GET
	@Path("reporteEspectaculo")
	public String reporteDeUnEspectaculo(@QueryParam("idEspectaculo") int idEspectaculo)
	{
		System.out.println("_______GENERANDO REPORDE DE UNA FUNCION______");
		System.out.println("--->bucar el espectaculo con id:"+idEspectaculo);
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		String res;
		try {
			res = tm.reporteDeUnEspectaculo(idEspectaculo);
		} catch (Exception e) {
			return "Error en la consulta del Query";
		}
		return res;

	}
	
	@GET
	@Path("consultarSitio")
	public String reporteDeUnSitio(@QueryParam("idSitio") int idSitio)
	{
		System.out.println("_______GENERANDO REPORDE DE UN SITIO______");
		System.out.println("--->bucar el sitio con id:"+idSitio);
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		String res;
		try {
			res = tm.reporteDeUnSitio(idSitio);
		} catch (Exception e) {
			return "Error en la consulta del Query";
		}
		return res;

	}
	
	@GET
	@Path("consultarFuncionesEspectaculosProgramados")
	public String consultarFuncionesEspectaculosProgramados(
						@QueryParam("xCategoria") String xCategoria,
						@QueryParam("xIdioma") String xIdioma,
						@QueryParam("xFechaInicio") String xFechaInicio,
						@QueryParam("xFechaFinal") String xFechaFinal,
						@QueryParam("Servicio Traduccion") int serTra)
	{
		System.out.println("_______GENERANDO REPORDE DE LAS FUNCIONES______");
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		String res;
		try {
			res = tm.consultarFuncionesEspectaculosProgramados(xCategoria,xIdioma,serTra,xFechaInicio,xFechaFinal);
		} catch (Exception e) {
			return "Error en la consulta del Query";
		}
		return res;
	}
	
	@GET
	@Path("bono2")
	public String bono2(@QueryParam("fechaInicio") String f1, @QueryParam("fechaFinal") String f2 )
	{
		// probar con 11/01/2017 00:0 y 25/07/2017 00:00
		System.out.println("_______GENERANDO REPORDE DEl ESPECTACULO MAS VENDIDO______");
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		String res;
		try {
			res = tm.bono2(f1,f2);
		} catch (Exception e) {
			return "Error en la consulta del Query";
		}
		return res;

	}
}
