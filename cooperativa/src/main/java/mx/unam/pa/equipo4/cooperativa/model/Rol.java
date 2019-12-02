package mx.unam.pa.equipo4.cooperativa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Definicion de la clase entidad para la tabla rol
@Entity
@Table(name = "rol")
public class Rol {
	
	// Atributo de identificacion id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rol_id")
	private int id;
	
	// Atributo con la descripcion del rol 
	@Column(name = "descripcion", nullable = false, length = 255)
	private String descripcion;
	
	// Constructor Default
	public Rol() {
		super();
	}
	
	// Constructor con descripcion
	public Rol(String descripcion) {
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
	
	// Metodo para imprimir el contenido del objeto
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rol [id=").append(id).append(", descripcion=").append(descripcion).append("]");
		return builder.toString();
	}
	
}