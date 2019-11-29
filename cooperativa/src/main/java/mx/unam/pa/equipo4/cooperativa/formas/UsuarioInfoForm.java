package mx.unam.pa.equipo4.cooperativa.formas;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UsuarioInfoForm {
	
	@NotNull
	@Size(min = 2, max = 45, message = "Longitud requerida de 2 a 45 caracteres")
	private String username;
	
	@NotNull
	@Size(min = 2, max = 45, message = "Longitud requerida de 2 a 45 caracteres")
	private String password;
	
	@NotNull
	@Size(min = 2, max = 45, message = "Longitud requerida de 2 a 45 caracteres")
	private String nombre;
	
	@NotNull
	@Size(min = 2, max = 45, message = "Longitud requerida de 2 a 45 caracteres")
	private String apellidos;
	
	@Pattern(regexp=".*@.*",message = "Correo inválido.")
	private String correo;
	
	@Pattern(regexp="[0-9]{8,10}",message = "Teléfono. Sólo números.")
	private String telefono;
	
	public String getUsername() {
		return username;
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
