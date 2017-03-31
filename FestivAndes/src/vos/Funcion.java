package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Funcion 
{
	//atributos 
		@JsonProperty(value="idFuncion")
		private int idFuncion;
		
		//debe cumplir los requerimientos del espectaculo
		@JsonProperty(value="idSitio")
		private int idSitio;
		
		@JsonProperty(value="idEspectaculo")
		private int idEspectaculo;

		@JsonProperty(value="nombre")
		private String nombre;

		@JsonProperty(value="fecha")
		private String fecha;

		@JsonProperty(value="duracion")
		private int duracion;
		
		@JsonProperty(value="costoBoleta")
		private int costoBoleta;

		public Funcion (
				@JsonProperty(value="idFuncion") int idFuncion, 
				@JsonProperty(value="idSitio") int idSitio,
				@JsonProperty(value="idEspectaculo") int idEspec,
				@JsonProperty(value="nombre") String nombre,
				@JsonProperty(value="fecha") String fecha,
				@JsonProperty(value="duracion") int duracion,
				@JsonProperty(value="costoBoleta") int costoBoleta)
		{
			super();
			this.idFuncion = idFuncion;
			this.idSitio = idSitio;
			this.idEspectaculo = idEspec;
			this.nombre = nombre;
			this.fecha = fecha;
			this.duracion = duracion;
			this.costoBoleta = costoBoleta;
		}

		
		public int getIdFuncion() {
			return idFuncion;
		}

		public void setIdFuncion(int idFuncion) {
			this.idFuncion = idFuncion;
		}

		public int getIdSitio() {
			return idSitio;
		}

		public void setIdSitio(int idSitio) {
			this.idSitio = idSitio;
		}

		public int getIdEspectaculo() {
			return idEspectaculo;
		}

		public void setIdEspectaculo(int idEspectaculo) {
			this.idEspectaculo = idEspectaculo;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public String getFecha() {
			return fecha;
		}

		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		public int getDuracion() {
			return duracion;
		}

		public void setDuracion(int duracion) {
			this.duracion = duracion;
		}


		public int getCostoBoleta() {
			return costoBoleta;
		}

		public void setCostoBoleta(int costoBoleta) {
			this.costoBoleta = costoBoleta;
		}
}
