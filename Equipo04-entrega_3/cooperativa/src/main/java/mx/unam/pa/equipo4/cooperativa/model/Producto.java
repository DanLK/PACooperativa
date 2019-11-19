package mx.unam.pa.equipo4.cooperativa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "producto_id")
	private int id;
	
	@Column(name = "nombre", nullable = false, length = 255)
	private String nombre;
	
	@Column(name = "contenido", length = 45)
	private String contenido;
	
	@Column(name = "precio", nullable = false)
	private float precio;
	
	@Column(name = "departamento", length = 45)
	private String departamento;
	
	public Producto() {
		super();
	}
	
	public Producto(String nombre, String contenido, float precio, String departamento) {
		super();
		this.nombre = nombre;
		this.contenido = contenido;
		this.precio = precio;
		this.departamento = departamento;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getContenido() {
		return contenido;
	}
	
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Producto [id=").append(id).append(", nombre=").append(nombre).append(", contenido=").append(contenido).append(", precio=").append(precio).append("]");
		return builder.toString();
	}
	
}