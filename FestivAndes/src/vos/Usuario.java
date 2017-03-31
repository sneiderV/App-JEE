package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Usuario 
{
	//atributos 

	@JsonProperty(value="nombre")
	private String nombre;

	@JsonProperty(value="tipoIdent")
	private String tipoIdentificacion;

	@JsonProperty(value="ident")
	private int identificacion;
	
	@JsonProperty(value="clave")
	private int clave;

	@JsonProperty(value="rol")
	private String rol;
	
	@JsonProperty(value="email")
	private String email;


	public Usuario(@JsonProperty(value="nombre") String nombre, 
			@JsonProperty(value="tipoIdent") String tipoIdent,
			@JsonProperty(value="ident") int ident,
			@JsonProperty(value="clave") int clave,
			@JsonProperty(value="rol") String rol,
			@JsonProperty(value="email") String email)
	{
		super();
		this.nombre=nombre;
		this.tipoIdentificacion=tipoIdent;
		this.identificacion=ident;
		this.clave=clave;
		this.rol=rol;
		this.email=email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}

	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}

	public int getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(int identificacion) {
		this.identificacion = identificacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

}
