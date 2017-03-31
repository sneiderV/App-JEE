package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.FestivAndesMaster;
import vos.CompraBoletas;

@Path("clienteServ")
public class ClienteServices 
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
	//////////////////////////////SERVICIOS DEL CLIENTE////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * RF7
	 * 
	 * @param id
	 * @param prefCategoria
	 * @param prefSitio
	 * @return
	 */
	@POST
	@Path("/regPreferenciaCliente")
	public String addModPreferenciaCliente(
					@QueryParam("identificacion")int id,
					@QueryParam("prefeCategoria")String prefCategoria,
					@QueryParam("prefeSitio")String prefSitio ) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.addModPreferenciaCliente(id, prefCategoria, prefSitio);
		} catch (Exception e) {
			return "Error en el query";
		}
				
		return "se modificaron las preferencias del cliente con identificacion: "+id;
	}
	
	/**
	 * RF8
	 * 
	 * Metodo que expone servicio REST usando PUT que COMPRA una BOLETA que recibe en Json
	 * <b>URL: </b> http://"ip o nombre de host":8080/FestivAndes/rest/adminServ/regCliente
	 * @param boleta - boleta a agregar
	 * @return Json con el cliente que agrego o Json con el error que se produjo
	 */
	@PUT
	@Path("comprarBoleta")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addComprarBoleta(CompraBoletas boleta) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try
		{
			tm.addComprarBoletas(boleta);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(boleta).build();
	}


}
