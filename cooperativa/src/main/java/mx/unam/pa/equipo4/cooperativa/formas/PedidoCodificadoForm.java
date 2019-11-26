package mx.unam.pa.equipo4.cooperativa.formas;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PedidoCodificadoForm {
	@NotNull
	@Size(min = 2, max = 45, message = "Logintud requerida de 2 a 45 caracteres")
	private String pedidoCodigo;
	
	@NotNull
	private float pedidoTotal;
	
	public String getPedidoCodigo() {
		return pedidoCodigo;
	}
	
	public void setPedidoCodigo(String pedidoCodigo) {
		this.pedidoCodigo = pedidoCodigo;
	}
	
	public float getPedidoTotal() {
		return pedidoTotal;
	}
	
	public void setPedidoTotal(float pedidoTotal) {
		this.pedidoTotal = pedidoTotal;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PedidoCodificadoForm [pedidoCodigo=").append(pedidoCodigo).append("]");
		return builder.toString();
	}
}
