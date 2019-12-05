package mx.unam.pa.equipo4.cooperativa.formas;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Clase que define los parametros para la forma de Log In
public class LoginFrm {
	
	// Atributo de nombre de usuario, username
	@NotNull
	@Size(min = 2, max = 45, message = "Logintud requerida de 2 a 45 caracteres")
	private String username;
	
	// Atributo de la contraseña de acceso
	@NotNull
	@Size(min = 2, max = 45, message = "Logintud requerida de 2 a 45 caracteres")
	private String password;
	
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
	
	// Metodo para imprimir el contenido del objeto
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginFrm [username=").append(username).append(", password=").append(password).append("]");
		return builder.toString();
	}
}
