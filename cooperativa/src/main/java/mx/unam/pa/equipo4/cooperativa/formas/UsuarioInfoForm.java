package mx.unam.pa.equipo4.cooperativa.formas;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

// Clase que define los parametros para la forma para dar de alta o modificar un usuario
public class UsuarioInfoForm {
	
	// Atributo identificador id
	@NotNull
	private int id;
	
	// Atributo con el nombre de la cuenta, username
	@NotNull
	@Size(min = 1, max = 45, message = "Longitud requerida de 1 a 45 caracteres")
	private String username;
	
	// Atributo de la contraseña de acceso
	@NotNull
	@Size(min = 1, max = 45, message = "Longitud requerida de 1 a 45 caracteres")
	private String password;
	
	// Atributo del nombre de la persona dueña de la cuenta
	@NotNull
	@Size(min = 1, max = 45, message = "Longitud requerida de 1 a 45 caracteres")
	private String nombre;
	
	// Atributo de los apellidos de la persona dueña de la cuenta
	@NotNull
	@Size(min = 1, max = 45, message = "Longitud requerida de 1 a 45 caracteres")
	private String apellidos;
	
	// Atributo del correo de contacto del usuario
	@Pattern(regexp="^(.+@.+\\..+)*$", message="Introduce una direccion de correo valida")
	private String correo;
	
	// Atributo del telefono de contacto del usuario
	@Pattern(regexp="(^[0-9]{8,10})*$", message="Introduce un Telefono Valido de 8 a 10 digitos")
	private String telefono;
	
	// Atributo con el identificador del rol que tiene el usuario
	private int idrol;
	
	public int getIdrol() {
		return idrol;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPassword(){
		return password;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}