package rest;


import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import vos.Video;
import vos.Funcion;
import vos.ListaVideos;
import vos.Usuario;

/**
 * Clase que expone servicios REST con ruta base: http://"ip o nombre de host":8080/VideoAndes/rest/videos/...
 * Clase que expone servicios REST con ruta base: http://:8080/FestivAndes/rest/sistema/...
 */
@Path("sistema")
public class FestivAndesServices {

	// Servicios REST tipo GET:

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
	
	@GET
	@Path("sayHello")
		public String sayHello(@QueryParam("name")String name) 
	{
		System.out.println("aasaaa");
		return "asa: "+name;
	}
	
	/**
	 * http://:8080/FestivAndes/rest/sistema/funcID
	 */
	@GET
	@Path("funcID")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getFuncionporId(@QueryParam("idFuncion") int idFunc) 
	{
		System.out.println("bucar la funcion con id:"+idFunc);
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		Funcion func;
		try {
			func = tm.buscarFuncionPorId(idFunc);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(func).build();
	}

	/**
	 * Método que expone servicio REST usando GET que da todos los videos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
	 * @return Json con todos los videos de la base de datos O json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVideos() {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ListaVideos videos;
		try {
			videos = tm.darVideos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(videos).build();
	}


    /**
     * Método que expone servicio REST usando GET que busca el video con el nombre que entra como parámetro
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/name/"name para la busqueda"
     * @param name - Nombre del video a buscar que entra en la URL como parámetro 
     * @return Json con el/los videos encontrados con el nombre que entra como parámetro o json con 
     * el error que se produjo
     */
	@GET
	@Path("/name/{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVideoName(@javax.ws.rs.PathParam("name") String name) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ListaVideos videos;
		try {
			if (name == null || name.length() == 0)
				throw new Exception("Nombre del video no valido");
			videos = tm.buscarVideosPorName(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(videos).build();
	}
	
    /**
     * Método que expone servicio REST usando GET que busca el video mas alquilado
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/MayorAlquilado
     * @return Json con el/los videos encontrados con el nombre que entra como parámetro o json con 
     * el error que se produjo
     */
	@GET
	@Path("/MayorAlquilado")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVideoMayorAlquilado() {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		ListaVideos videos;
		try {
			videos = tm.videosMasAlquilados();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(videos).build();
	}


    /**
     * Método que expone servicio REST usando PUT que agrega el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a agregar
     * @return Json con el video que agrego o Json con el error que se produjo
     */
//	@PUT
//	@Path("/video")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addVideo(Video video)
//	{
//		FestivAndesMaster tm = new FestivAndesMaster(getPath());
//		try {
//			tm.addVideo(video);
//		} catch (Exception e) {
//			return Response.status(500).entity(doErrorMessage(e)).build();
//		}
//		return Response.status(200).entity(video).build();
//	}
//	
    /**
     * Método que expone servicio REST usando PUT que agrega los videos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/videos
     * @param videos - videos a agregar. 
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@PUT
	@Path("/videos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVideo(ListaVideos videos) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try {
			tm.addVideos(videos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(videos).build();
	}
	
    /**
     * Método que expone servicio REST usando POST que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a actualizar. 
     * @return Json con el video que actualizo o Json con el error que se produjo
     */
	@POST
	@Path("/video")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateVideo(Video video) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try {
			tm.updateVideo(video);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(video).build();
	}
	
    /**
     * Método que expone servicio REST usando DELETE que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a aliminar. 
     * @return Json con el video que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/video")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVideo(Video video) {
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try {
			tm.deleteVideo(video);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(video).build();
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	  
	 /**
	 * RF1
	 *  
     * Metodo que expone servicio REST usando PUT que agrega el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a agregar
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@PUT
	@Path("/registrarUsuario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrarUsuario(Usuario usuario) 
	{
		FestivAndesMaster tm = new FestivAndesMaster(getPath());
		try 
		{
			tm.crearUsuario(usuario);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		System.out.println(usuario.toString());
		return Response.status(200).entity(usuario).build();
	}

}
