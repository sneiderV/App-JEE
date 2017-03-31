package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class Sitio 
{
	//atributos 

		@JsonProperty(value="idSitio")
		private int idSitio;
		
		@JsonProperty(value="nombre")
		private String nombre;
		
		@JsonProperty(value="capacidad")
		private int capacidad;
		
		@JsonProperty(value="apto_per_espec")
		private int aptoPerEspec;
		
		@JsonProperty(value="hora_dispo_inic")
		private int horaInic;
		
		@JsonProperty(value="hora_dispo_final")
		private int horaFinal;

		//propenso a ser una tabla, ampliacion de sonido, efectos de luz, fuegos artificiales ...
		@JsonProperty(value="condi_tec")
		private String condiTec;

		//propenso a arraylist movil,fija,removible
		@JsonProperty(value="tipo_silleteria")
		private String tip_sille;
		
		@JsonProperty(value="proteccion_exterior")
		private int prot_ext;
		
		//vip, plateadas, intermedia, general, etc
		@JsonProperty(value="localidades")
		private String localidades;
		
		

		public Sitio(
				@JsonProperty(value="idSitio") int id, 
				@JsonProperty(value="nombre") String nombre,
				@JsonProperty(value="capacidad") int cap,
				@JsonProperty(value="apto_per_espec") int aptoperEspec,
				@JsonProperty(value="hora_dispo_inic") int horaInic,
				@JsonProperty(value="hora_dispo_final") int horaFinal,
				@JsonProperty(value="condi_tec") String condiTec,
				@JsonProperty(value="tipo_silleteria") String tipSille,
				@JsonProperty(value="proteccion_exterior") int proteccExt,
				@JsonProperty(value="localidades") String localidades
				)
		{
			super();
			this.idSitio = id;
			this.nombre = nombre;
			this.capacidad = cap;
			this.aptoPerEspec = aptoperEspec;
			this.horaInic= horaInic;
			this.horaFinal = horaFinal;
			this.condiTec = condiTec;
			this.tip_sille= tipSille;
			this.prot_ext = proteccExt;
			this.localidades = localidades;
			}



		public int getIdSitio() {
			return idSitio;
		}



		public void setIdSitio(int idSitio) {
			this.idSitio = idSitio;
		}



		public String getNombre() {
			return nombre;
		}



		public void setNombre(String nombre) {
			this.nombre = nombre;
		}



		public int getCapacidad() {
			return capacidad;
		}



		public void setCapacidad(int capacidad) {
			this.capacidad = capacidad;
		}



		public int getAptoPerEspec() {
			return aptoPerEspec;
		}



		public void setAptoPerEspec(int aptoPerEspec) {
			this.aptoPerEspec = aptoPerEspec;
		}



		public int getHoraInic() {
			return horaInic;
		}



		public void setHoraInic(int horaInic) {
			this.horaInic = horaInic;
		}



		public int getHoraFinal() {
			return horaFinal;
		}



		public void setHoraFinal(int horaFinal) {
			this.horaFinal = horaFinal;
		}



		public String getCondiTec() {
			return condiTec;
		}



		public void setCondiTec(String condiTec) {
			this.condiTec = condiTec;
		}



		public String getTip_sille() {
			return tip_sille;
		}



		public void setTip_sille(String tip_sille) {
			this.tip_sille = tip_sille;
		}



		public int getProt_ext() {
			return prot_ext;
		}



		public void setProt_ext(int prot_ext) {
			this.prot_ext = prot_ext;
		}



		public String getLocalidades() {
			return localidades;
		}



		public void setLocalidades(String localidades) {
			this.localidades = localidades;
		}

		

}
