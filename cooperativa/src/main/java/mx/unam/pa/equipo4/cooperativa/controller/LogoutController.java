package mx.unam.pa.equipo4.cooperativa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//Clase controlador para las operaciones sobre el Log Out
@Controller
public class LogoutController {
	
	// Definimos el metodo con las operaciones a realizar con /logout,
	//   que es guardar los cambios realizados a un pedido y redirigir al usuario al listado de pedidos
	@RequestMapping("/logout")
	public String cerrarSesionUsuario(HttpSession session) {
		System.out.println("Cerrando sesión de usuario");
		
		// Invalidar la sesion
		session.invalidate();
		
		System.out.println("Redireccionando a la pantalla de inicio de sesión");
		return "redirect:/spring/login";
	}
	
}
