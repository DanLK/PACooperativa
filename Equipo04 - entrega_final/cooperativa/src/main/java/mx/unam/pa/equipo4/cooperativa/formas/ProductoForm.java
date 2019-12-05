package mx.unam.pa.equipo4.cooperativa.formas;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Clase que define los parametros para la forma de dar de alta o modificar un producto
public class ProductoForm {
	
	// Atributo de identificacion id
	private int id;
	
	// Atributo con el nombre del producto
	@NotNull
	@Size(min = 2, max = 45, message = "Logintud requerida de 2 a 45 caracteres")
	private String nombre;
	
	// Atributo con el contenido del producto (1 pza, 50 gr, 1 caja, etc.)
	private String contenido;
	
	// Atributo con el precio unitario del producto
	@NotNull
	private float precio;
	
	// Atributo que describe el nombre del departamento al que pertenece el producto
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
	
	// Metodo para imprimir el contenido del objeto
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductoForm [ID=").append(id).append(", nombre=").append(nombre).append(", contenido=").append(contenido).append(", precio=").append(precio).append(", departamento=").append(departamento).append("]");
		return builder.toString();
	}
		
}

