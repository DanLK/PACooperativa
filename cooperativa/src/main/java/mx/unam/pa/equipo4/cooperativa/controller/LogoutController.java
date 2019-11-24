package mx.unam.pa.equipo4.cooperativa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping("/logout")
	public String cerrarSesionUsuario(HttpSession session) {
		System.out.println("Cerrando sesión de usuario");
		session.invalidate();
		
		System.out.println("Redireccionando a la pantalla de inicio de sesión");
		return "redirect:/spring/login";
	}
}
