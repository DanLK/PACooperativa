package mx.unam.pa.equipo4.cooperativa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pedido_id")
	private int id;
	
	@Column(name = "fecha_registro", nullable = false)
	private Date fechaRegistro;
	
	@Column(name = "total", nullable = false)
	private float total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_status_pedido_status_id")
	private PedidoStatus pedidoStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_usuario_id")
	private Usuario usuario;
	
	public Pedido() {
		super();
	}
	
	public Pedido(Date fechaRegistro, float total, PedidoStatus pedidoStatus, Usuario usuario) {
		super();
		this.fechaRegistro = fechaRegistro;
		this.total = total;
		this.pedidoStatus = pedidoStatus;
		this.usuario = usuario;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	} 
	
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public float getTotal() {
		return total;
	}
	
	public void setTotal(float total) {
		this.total = total;
	}
	
	public PedidoStatus getPedidoStatus() {
		return pedidoStatus;
	}
	
	public void setPedidoStatus(PedidoStatus pedidoStatus) {
		this.pedidoStatus = pedidoStatus;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido [id=").append(id).append(", Fecha Registro=").append(fechaRegistro).append(", total=").append(total).append(", PedidoStatus=").append(pedidoStatus).append(", Usuario=").append(usuario).append("]");
		return builder.toString();
	}
	
}