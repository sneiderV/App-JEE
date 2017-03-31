package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class FuncionRealizada 
{
	//atributos 
			@JsonProperty(value="idFuncion")
			private int idFuncion;
			
			//debe cumplir los requerimientos del espectaculo
			@JsonProperty(value="realizado")
			private int realizado;
		
			public FuncionRealizada (
					@JsonProperty(value="idFuncion") int idFuncion, 
					@JsonProperty(value="realizado") int realizado)
			{
				super();
				this.idFuncion = idFuncion;
				this.realizado = realizado;
			}

			public int getIdFuncion() {
				return idFuncion;
			}

			public void setIdFuncion(int idFuncion) {
				this.idFuncion = idFuncion;
			}

			public int getRealizado() {
				return realizado;
			}

			public void setRealizado(int realizado) {
				this.realizado = realizado;
			}
			
			
}
