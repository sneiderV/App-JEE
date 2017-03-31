package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class CompaniaDeTeatro 
{
	//atributos 
	@JsonProperty(value="idComp")
	private int idComp;

	@JsonProperty(value="nombre")
	private String nombre;

	@JsonProperty(value="representante")
	private String representante;

	@JsonProperty(value="paisOrigen")
	private String paisOrigen;

	@JsonProperty(value="pagWeb")
	private String pagWeb;


	public CompaniaDeTeatro (@JsonProperty(value="idComp") int idComp, 
			@JsonProperty(value="nombre") String nombre,
			@JsonProperty(value="representante") String repres,
			@JsonProperty(value="paisOrigen") String paisOrigen,
			@JsonProperty(value="pagWeb") String pagWeb)
	{
		super();
		this.idComp = idComp; 
		this.nombre = nombre;
		this.representante = repres;
		this.paisOrigen = paisOrigen;
		this.pagWeb = pagWeb;
	}

	public int getIdComp() {
		return idComp;
	}

	public void setIdComp(int idComp) {
		this.idComp = idComp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public String getPagWeb() {
		return pagWeb;
	}

	public void setPagWeb(String pagWeb) {
		this.pagWeb = pagWeb;
	}

	
	

}
