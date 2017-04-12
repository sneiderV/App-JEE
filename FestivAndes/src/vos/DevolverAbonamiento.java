package vos;

import org.codehaus.jackson.annotate.JsonProperty;

public class DevolverAbonamiento 
{
	@JsonProperty(value="idCliente")
	private int idCliente;
	
	@JsonProperty(value="idBoletas")
	private int [] idBoletas;
	
	public DevolverAbonamiento(	@JsonProperty(value="idCliente") int idCliente , @JsonProperty(value="idFuncion") int [] idBoletas)
	{
		super();
		this.idCliente = idCliente;
		this.idBoletas = idBoletas;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int[] getIdBoletas() {
		return idBoletas;
	}

	public void setIdBoletas(int[] idBoletas) {
		this.idBoletas = idBoletas;
	}
	
	
}
