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

// Clase controlador para las operaciones sobre modificar la informacion del usuario en sesion
@Controller
@SessionAttributes("usuarioFirmado")
public class UsuarioInfoController {
	@Autowired
	UsuarioService usuarioservice;
	
	// Definimos el metodo con las operaciones a realizar con /usuarioinfo,
	//   que es mostrar la informacion del usuario en sesion junto el formulario para modificarla
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
		  
		  // Agregamos al objeto de la informacion usuario en sesion
		  mav.addObject("informacionUsuarioFirmado", usuarioEnSesion);
		  
		  return mav;
		  
	}
	
	// Definimos el metodo con las operaciones a realizar con /editarInfo,
	//   que es modificar la informacion del usuario con la informacion del formulario
	@RequestMapping( value = "/editarInfo", method = RequestMethod.POST )
	public ModelAndView editarInfoUsuario(@Valid @ModelAttribute("formUsuarioInfo") UsuarioInfoForm usuarioinform,
			BindingResult resultado, // Resultado de la validación 
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			ModelAndView view //  modelo a regresar
		) {
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma presentó errores");
			view.setViewName("usuarioInfo");
			view.addObject("usuarioFirmado", usuarioEnSesion); 
			return view;
		}
		
		// Validamos si esta cambiando el username, y este ya existe
		// Obtener username actual y si es distinto lo esta cambiando
		// Checar si ya existe ese username
		if (usuarioEnSesion.getUsername().equals(usuarioinform.getUsername()) == false) {
			Usuario validateUsuario = usuarioservice.getUsuario(usuarioinform.getUsername());
			if (validateUsuario != null) {
				
				System.err.println("El username ya esta en uso");
				view.setViewName("usuarioInfo");
				
				// Agregamos el usuario en Sesion
				view.addObject("usuarioFirmado", usuarioEnSesion); 
				
				// Agregamos el error
				view.addObject("error", "Username ocupado");
				
				return view;
				
			}
		}
		
		// Desalojamos la instancia del usuario
		usuarioservice.desalojar(usuarioEnSesion);
		
		// Modificamos la informacion con la informacion del formulario
		usuarioEnSesion.setNombre(usuarioinform.getNombre());
		usuarioEnSesion.setApellidos(usuarioinform.getApellidos());
		usuarioEnSesion.setUsername(usuarioinform.getUsername());
		usuarioEnSesion.setPassword(usuarioinform.getPassword());
		usuarioEnSesion.setCorreo(usuarioinform.getCorreo());
		usuarioEnSesion.setTelefono(usuarioinform.getTelefono());
		
		// Guardamos la instancia en la base de datos
		usuarioservice.actualizar(usuarioEnSesion);
		
		// Obtenemos la vista
		view.setViewName("usuarioInfo");
		
		// Agregamos el usuario Firmado a sesion
		view.addObject("usuarioFirmado", usuarioEnSesion);
		
		return view;
	}
	

}