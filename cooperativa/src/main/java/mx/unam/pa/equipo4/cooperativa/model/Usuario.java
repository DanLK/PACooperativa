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

// Definicion de la clase entidad para la tabla usuario
@Entity
@Table(name = "usuario")
public class Usuario {
	
	// Atributo de identificacion id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id")
	private int id;
	
	// Atributo del nombre de la persona dueña de la cuenta
	@Column(name = "nombre", nullable = false, length = 45)
	private String nombre;
	
	// Atributo de los apellidos de la persona dueña de la cuenta
	@Column(name = "apellidos", nullable = false, length = 45)
	private String apellidos;
	
	// Atributo del nombre para identificar la cuenta, el username
	@Column(name = "username", nullable = false, length = 45)
	private String username;
	
	// Atributo para definir la contraseña de acceso
	@Column(name = "password", nullable = false, length = 255)
	private String password;
	
	// Atributo para definir un correo como contacto
	@Column(name = "correo", length = 255)
	private String correo;
	
	// Atributo para definir un telefono como contacto
	@Column(name = "telefono", length = 255)
	private String telefono;
	
	// Atributo de tipo Rol que define el rol que tiene el usuario (Administrador o socio)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rol_rol_id")
	private Rol rol;
	
	// Constructor Default
	public Usuario() {
		super();
	}
	
	// Constructor con todos los atributos menos el identificador
	public Usuario(String nombre, String apellidos, String username, String password, String correo, String telefono, Rol rol) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.username = username;
		this.password = password;
		this.correo = correo;
		this.telefono = telefono;
		this.rol = rol;
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
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Rol getRol() {
		return rol;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	// Metodo para imprimir el contenido del objeto
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [id=").append(id).append(", nombre=").append(nombre).append(", apellidos=").append(apellidos).append(", username=").append(username).append(", password=").append(password).append(", correo=").append(correo).append(", telefono=").append(telefono).append(", Rol=").append(rol).append("]");
		return builder.toString();
	}
	
}