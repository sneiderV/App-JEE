package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Cliente 
{
	//atributos 

		@JsonProperty(value="nombre")
		private String nombre;

		@JsonProperty(value="tipoIdent")
		private String tipoIdentificacion;

		@JsonProperty(value="ident")
		private int identificacion;

		@JsonProperty(value="email")
		private String email;

		@JsonProperty(value="ciudad_residencia")
		private String ciudadResidencia;
		
		@JsonProperty(value="fecha_Nacimiento")
		private String fechaNacimiento;
		
		@JsonProperty(value="prefeCategoria")
		private String prefeCategoria;
		
		@JsonProperty(value="prefeSitio")
		private String prefeSitio;
		
		public Cliente(@JsonProperty(value="nombre") String nombre, 
				@JsonProperty(value="tipoIdent") String tipoIdent,
				@JsonProperty(value="ident") int ident,
				@JsonProperty(value="email") String email,
				@JsonProperty(value="ciudad_residencia") String ciuRes,
				@JsonProperty(value="fecha_nacimiento") String fechaNac,
				@JsonProperty(value="prefeCategoria") String prefeCategoria,
				@JsonProperty(value="prefeSitio") String prefeSitio )
		{
			super();
			this.nombre=nombre;
			this.tipoIdentificacion=tipoIdent;
			this.identificacion=ident;
			this.email=email;
			this.ciudadResidencia=ciuRes;
			this.fechaNacimiento = fechaNac;
			this.prefeCategoria = prefeCategoria;
			this.prefeSitio = prefeSitio;
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

		public String getCiudadResidencia() {
			return ciudadResidencia;
		}

		public void setCiudadResidencia(String ciudadResidencia) {
			this.ciudadResidencia = ciudadResidencia;
		}

		public String getFechaNacimiento() {
			return fechaNacimiento;
		}

		public void setFechaNacimiento(String fechaNacimiento) {
			this.fechaNacimiento = fechaNacimiento;
		}

		public String getPrefeCategoria() {
			return prefeCategoria;
		}

		public void setPrefeCategoria(String prefeCategoria) {
			this.prefeCategoria = prefeCategoria;
		}

		public String getPrefeSitio() {
			return prefeSitio;
		}

		public void setPrefeSitio(String prefeSitio) {
			this.prefeSitio = prefeSitio;
		}
}
