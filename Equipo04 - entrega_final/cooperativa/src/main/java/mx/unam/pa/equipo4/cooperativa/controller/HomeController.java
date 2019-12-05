package mx.unam.pa.equipo4.cooperativa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import mx.unam.pa.equipo4.cooperativa.formas.LoginFrm;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

// Clase controlador para las operaciones sobre la pagina de inicio
@Controller
public class HomeController {
	
	// Definimos el metodo con las operaciones a realizar con /,
	//   que es validar si el usuario tiene sesion para mostrarle la pagina de inicio o el login en otro caso
	@GetMapping("/")
	public ModelAndView queHayEnSesion(
				@SessionAttribute(
						name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
						required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
			) {
		System.out.println("Objeto recuperado de la sesión de usuario: " + usuarioEnSesion);
		
		ModelAndView view;
		
		// Si tiene sesion que le muestre la pagina de welcoe
		if( usuarioEnSesion != null ) {
			System.out.println("Usuario con sesion: " + usuarioEnSesion);
			view = new ModelAndView("welcome");
			view.addObject("usuarioFirmado", usuarioEnSesion); // Creación de la sesión de usuario
			
		}else {
			// Redirigirlo al login en caso contrario
			view = new ModelAndView("login", "formLogin", new LoginFrm());
		}
		
		return view;
	}
	
}
