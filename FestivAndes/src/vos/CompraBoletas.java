package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class CompraBoletas 
{
	//atributos 
		@JsonProperty(value="idBoleta")
		private int idBoleta;

		@JsonProperty(value="idFuncion")
		private int idFuncion;

		@JsonProperty(value="idCliente")
		private int idCliente;

		@JsonProperty(value="localidad")
		private String localidad;

		@JsonProperty(value="num_sillas")
		private int num_sillas;

		public CompraBoletas (
				@JsonProperty(value="idBoleta") int idBoleta , 
				@JsonProperty(value="idFuncion") int idFuncion,
				@JsonProperty(value="idCliente") int idCliente,
				@JsonProperty(value="localidad") String localidad,
				@JsonProperty(value="num_sillas") int num_sillas)
		{
			super();
			this.idBoleta = idBoleta; 
			this.idFuncion = idFuncion;
			this.idCliente = idCliente;
			this.localidad = localidad;
			this.num_sillas = num_sillas;
		}

		public int getIdBoleta() {
			return idBoleta;
		}

		public void setIdBoleta(int idBoleta) {
			this.idBoleta = idBoleta;
		}

		public int getIdFuncion() {
			return idFuncion;
		}

		public void setIdFuncion(int idFuncion) {
			this.idFuncion = idFuncion;
		}

		public int getIdCliente() {
			return idCliente;
		}

		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}

		public String getLocalidad() {
			return localidad;
		}

		public void setLocalidad(String localidad) {
			this.localidad = localidad;
		}

		public int getNum_sillas() {
			return num_sillas;
		}

		public void setNum_sillas(int num_sillas) {
			this.num_sillas = num_sillas;
		}
		
}
