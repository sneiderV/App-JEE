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
import vos.Cliente;
import vos.CompaniaDeTeatro;
import vos.Espectaculo;
import vos.Funcion;
import vos.Sitio;

/**
 * Clase que expone servicios REST con ruta base: http://:8080/FestivAndes/rest/adminServ/...
 * @author SneiderV
 *
 */
@Path("adminServ")
public class AdminServices 
{
	/**
	 * Atributo que usa la anotaciÃ³n @Context para tener el ServletContext de la conexiÃ³n actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * MÃ©todo que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
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
	//////////////////////////////SERVICIOS DEL ADMIN////////////////////////////////////////////////////////////////////////////////////////////////////////


	/**
	 * RF2
	 * 
	 * Metodo que expone servicio REST usando PUT que agrega un cliente que recibe en Json
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/adminServ/regCliente
	 * @param cliente - cliente a agregar
	 * @return Json con el cliente que agrego o Json con el error que se produjo
	 */
	@PUT
	@Path("/regCliente")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCliente(Cliente cliente) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try
		{
			tm.addCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}


	/**
	 * RF3
	 * 
	 * Metodo que expone servicio REST usando PUT que agrega una compañia de teatro que recibe en Json
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/adminServ/regCompania
	 * @param compania - compania a agregar
	 * @return Json con la compania que agrego o Json con el error que se produjo
	 */
	@PUT
	@Path("/regCompania")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCompañia(CompaniaDeTeatro compania) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try
		{
			tm.addCompania(compania);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(compania).build();
	}

	/**
	 * RF4
	 * 
	 * Metodo que expone servicio REST usando PUT que agrega un espectaculo que recibe en Json
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/adminServ/regEspectaculo
	 * @param espectaculo - espectaculo a agregar
	 * @return Json con el espectaculo que agrego o Json con el error que se produjo
	 */
	@PUT
	@Path("/regEspectaculo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEspectaculo(Espectaculo espectaculo) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try
		{
			tm.addEspectaculo(espectaculo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(espectaculo).build();
	}

	/**
	 * RF5
	 * 
	 * Metodo que expone servicio REST usando PUT que agrega un espectaculo que recibe en Json
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/adminServ/regEspectaculo
	 * @param espectaculo - espectaculo a agregar
	 * @return Json con el espectaculo que agrego o Json con el error que se produjo
	 */
	@PUT
	@Path("/regSitio")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addSitio(Sitio sitio) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try
		{
			tm.addSitio(sitio);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(sitio).build();
	}

	/**
	 * RF6
	 * 
	 * Metodo que expone servicio REST usando PUT que agrega una funcion que recibe en Json
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/adminServ/regFuncion
	 * @param funcion - funcion a agregar
	 * @return Json con el funcion que agrego o Json con el error que se produjo
	 */
	@PUT
	@Path("/regFuncion")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addFuncion(Funcion funcion) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try
		{
			tm.addFuncion(funcion);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(funcion).build();
	}

///////////////////////////////////////////// ip vol2.0 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	/**
	 * RF14 v2.0
	 * 
	 * @param idFuncion
	 * @return
	 */
	@PUT
	@Path("cancelarFuncion")
	public String cancelarFuncion(@QueryParam("idFuncion") int idFuncion) 
	{
		System.out.println("---> Se quiere cancelar la funcion "+idFuncion);
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		String res;
		try
		{
			res = tm.cancelarFuncion(idFuncion);
		} catch (Exception e) {
			return "Error al devolver la boleta  //(Query)";
		}
		return res;
	}
}
