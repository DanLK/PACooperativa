package mx.unam.pa.equipo4.cooperativa.formas;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductoForm {
	
	private int id;
	
	@NotNull
	@Size(min = 2, max = 45, message = "Logintud requerida de 2 a 45 caracteres")
	private String nombre;
	
	private String contenido;
	
	@NotNull
	private float precio;
	
	private String departamento;
	
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
		this.precio  = precio;
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
		builder.append("ProductoForm [ID=").append(id).append(", nombre=").append(nombre).append(", contenido=").append(contenido).append(", precio=").append(precio).append(", departamento=").append(departamento).append("]");
		return builder.toString();
	}
		
}

