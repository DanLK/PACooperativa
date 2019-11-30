package mx.unam.pa.equipo4.cooperativa.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.unam.pa.equipo4.cooperativa.formas.UsuarioInfoForm;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.UsuarioService;

@Controller
@SessionAttributes("usuarioFirmado")
public class UsuarioInfoController {
	@Autowired
	UsuarioService usuarioservice;
	
	@GetMapping("/usuarioinfo")
	public ModelAndView informacionUsuario(@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
		  System.out.println("Mostrando forma de informacion de usuario");
		  ModelAndView mav = new ModelAndView("usuarioInfo", "formUsuarioInfo", new UsuarioInfoForm());
		  
		// Agregamos al objeto de usuario en sesion
		  mav.addObject("usuarioFirmado", usuarioEnSesion);
		  
		  return mav;
		  
	}
	
	@RequestMapping( value = "/editarInfo", method = RequestMethod.POST )
	public ModelAndView editarInfoUsuario(@Valid @ModelAttribute("formUsuarioInfo") UsuarioInfoForm usuarioinform,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			BindingResult resultado, // Resultado de la validación 
			ModelAndView view //  modelo a regresar
		) {
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma presentó errores");
			view.setViewName("usuarioInfo");
			view.addObject("usuarioFirmado", usuarioEnSesion); 
			return view;
		}
		
		view.setViewName("usuarioInfo");
		usuarioservice.desalojar(usuarioEnSesion);
		
		usuarioEnSesion.setNombre(usuarioinform.getNombre());
		usuarioEnSesion.setApellidos(usuarioinform.getApellidos());
		usuarioEnSesion.setUsername(usuarioinform.getUsername());
		usuarioEnSesion.setPassword(usuarioinform.getPassword());
		usuarioEnSesion.setCorreo(usuarioinform.getCorreo());
		usuarioEnSesion.setTelefono(usuarioinform.getTelefono());
		
		usuarioservice.actualizar(usuarioEnSesion);
		view.addObject("usuarioFirmado", usuarioEnSesion); 
		return view;
	}
	
	

}