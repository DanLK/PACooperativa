package mx.unam.pa.equipo4.cooperativa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// Definicion de la clase entidad para la tabla producto_pedido
@Entity
@Table(name = "producto_pedido")
public class ProductoPedido {
	
	// Atributo de identificacion id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "producto_pedido_id")
	private int id;
	
	// Atributo que describe cuantas unidades del producto estan en el pedido
	@Column(name = "cantidad", nullable = false)
	private int cantidad;
	
	// Atributo que es de tipo Pedido que es el pedido al que pertenece
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_pedido_id")
	private Pedido pedido;
	
	// Atributo quue es de tipo Producto que identifica a cual producto hace referencia
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_producto_id")
	private Producto producto;
	
	// Constructor default
	public ProductoPedido() {
		super();
	}
	
	// Constructor con todos los atributos excepto el id
	public ProductoPedido(int cantidad, Pedido pedido, Producto producto) {
		super();
		this.cantidad = cantidad;
		this.pedido = pedido;
		this.producto = producto;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	} 
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	// Metodo para imprimir el contenido del objeto
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductoPedido [id=").append(id).append(", cantidad=").append(cantidad).append(", producto=").append(producto).append(", Pedido=").append(pedido).append("]");
		return builder.toString();
	}
	
}