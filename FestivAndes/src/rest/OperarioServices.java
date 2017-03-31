package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.FuncionRealizada;

/**
  * Clase que expone servicios REST con ruta base: http://:8080/FestivAndes/rest/operaServ/...
  * @author SneiderV
  *
  */

@Path("operaServ")
public class OperarioServices 
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


	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}

	// METODO USADO PARA PROBAR EL URI CORRECTO \\
	@GET
	@Path("sayHello")
	public String sayHello(@QueryParam("name")String name) 
	{
		System.out.println("aasaaa");
		return "asa: "+name;
	}
	//////////////////////////////SERVICIOS DEL OPERARIO////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * RF9
	 * 
	 * Metodo que expone servicio REST usando PUT que agrega un cliente que recibe en Json
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/adminServ/regCliente
	 * @param cliente - cliente a agregar
	 * @return Json con el cliente que agrego o Json con el error que se produjo
	 */
	@PUT
	@Path("/regRealizacionFuncion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addRegRealizacionFuncion(FuncionRealizada funRealiza) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try
		{
			tm.addRegRealizacionFuncion(funRealiza);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funRealiza).build();
	}


}
