package mx.unam.pa.equipo4.cooperativa.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.unam.pa.equipo4.cooperativa.formas.LoginFrm;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.UsuarioService;

// Clase controlador para las operaciones sobre el Log In
@Controller
@SessionAttributes("usuarioFirmado")
public class LoginController {
  @Autowired
  UsuarioService usuarioService;
  
  // Definimos el metodo con las operaciones a realizar con /login,
  //   que es mostrar la vista con el formulario para iniciar sesion
  @GetMapping("/login")
  public ModelAndView mostrarFormaLogin() {
	  System.out.println("Mostrando forma de login");
	  
	  // Obtenemos la vista y agregamos el formulario necesario
	  ModelAndView mav = new ModelAndView("login", "formLogin", new LoginFrm());
	  
	  return mav;
  }
  
  // Definimos el metodo con las operaciones a realizar con /loginProcess,
  //   que es verificar la informacion de acceso en el formulario
  @RequestMapping( value = "/loginProcess", method = RequestMethod.POST )
  public ModelAndView loginUsuario(@Valid @ModelAttribute("formLogin") LoginFrm frmLogin,
			BindingResult resultado, // Resultado de la validación 
			ModelAndView view //  modelo a regresar
		) {
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma presentó errores");
			view.setViewName("login");
			return view;
		}
		
		System.out.println("Buscando al usuario en la DB con el criterio: " + frmLogin);
		
		// Llamamos el service de usuario para validar la informacion del formulario
		Usuario usuarioFirmado = usuarioService.validarUsuario(frmLogin);
		
		if( usuarioFirmado != null ) { // autenticación correcta
			System.out.println("Usuario encontrado en la DB: " + usuarioFirmado);
			view.addObject("usuarioFirmado", usuarioFirmado); // Creación de la sesión de usuario
			view.setViewName("redirect:/");
		}else {
			System.out.println("Usuario no encontrado en la DB");
			view.addObject("error", "Nombre de usuario y/o contraseña incorrectos");
			view.setViewName("login");
		}
		return view;
	}
  
}