package mx.unam.pa.equipo4.cooperativa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pedido_status")
public class PedidoStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_status_id")
	private int id;
	
	@Column(name = "descripcion", nullable = false, length = 255)
	private String descripcion;
	
	public PedidoStatus() {
		super();
	}
	
	public PedidoStatus(String descripcion) {
		super();
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PedidoStatus [id=").append(id).append(", descripcion=").append(descripcion).append("]");
		return builder.toString();
	}
	
}