package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Espectaculo 
{
	//atributos 

	@JsonProperty(value="idEspectaculo")
	private int idEspec;
	
	@JsonProperty(value="nombre")
	private String nombre;
	
	@JsonProperty(value="descripcion")
	private String descrip;
	
	@JsonProperty(value="categoria")
	private String categoria;
	
	@JsonProperty(value="duracion")
	private int duracion;

	@JsonProperty(value="idioma")
	private String idioma;

	@JsonProperty(value="costo_realizacion")
	private int costoRealizacion;

	@JsonProperty(value="requeri_tec")
	private String requeri_tec;
	
	@JsonProperty(value="serv_traduccion")
	private int serv_tradu;

	public Espectaculo(
			@JsonProperty(value="idEspectaculo") int id, 
			@JsonProperty(value="nombre") String nombre,
			@JsonProperty(value="descripcion") String descripcion,
			@JsonProperty(value="categoria") String categoria,
			@JsonProperty(value="duracion") int duracion,
			@JsonProperty(value="idioma") String idioma,
			@JsonProperty(value="costo_realizacion") int costo,
			@JsonProperty(value="requeri_tec") String req_tec,
			@JsonProperty(value="serv_traduccion") int traduc
			)
	{
		super();
		this.idEspec = id;
		this.nombre = nombre;
		this.descrip = descripcion;
		this.categoria = categoria;
		this.duracion = duracion;
		this.idioma = idioma;
		this.costoRealizacion = costo;
		this.requeri_tec = req_tec;
		this.serv_tradu = traduc;
		}

	public int getIdEspec() {
		return idEspec;
	}

	public void setIdEspec(int idEspec) {
		this.idEspec = idEspec;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public int getCostoRealizacion() {
		return costoRealizacion;
	}

	public void setCostoRealizacion(int costoRealizacion) {
		this.costoRealizacion = costoRealizacion;
	}

	public String getRequeri_tec() {
		return requeri_tec;
	}

	public void setRequeri_tec(String requeri_tec) {
		this.requeri_tec = requeri_tec;
	}

	public int getServ_tradu() {
		return serv_tradu;
	}

	public void setServ_tradu(int serv_tradu) {
		this.serv_tradu = serv_tradu;
	}


}
