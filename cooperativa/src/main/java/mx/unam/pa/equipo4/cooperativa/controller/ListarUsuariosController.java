package mx.unam.pa.equipo4.cooperativa.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import mx.unam.pa.equipo4.cooperativa.formas.UsuarioInfoForm;
import mx.unam.pa.equipo4.cooperativa.model.Rol;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.PedidoService;
import mx.unam.pa.equipo4.cooperativa.service.PedidoStatusService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoPedidoService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoService;
import mx.unam.pa.equipo4.cooperativa.service.RolService;
import mx.unam.pa.equipo4.cooperativa.service.UsuarioService;

//Clase controlador para las operaciones sobre los usuarios
@Controller
public class ListarUsuariosController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	PedidoStatusService pedidoStatusService;
	
	@Autowired
	ProductoPedidoService productoPedidoService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	UsuarioService usuarioService;
	
	// Definimos el metodo con las operaciones a realizar con /listarusuarios,
	//   que es mostrar todos los usuarios
	@GetMapping("/listarusuarios")
	public ModelAndView listarUsuarios(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
			System.out.println("Mostrando usuarios");
			ModelAndView mav = new ModelAndView("listarUsuarios");
			
			// Agregamos al objeto de usuario en sesion
			mav.addObject("usuarioFirmado", usuarioEnSesion);
			  
			// Solicitamos a la base de datos los usuarios
			List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
			
			// Revertimos la lista para mostrar primero a los mas nuevos
			Collections.reverse(listaUsuarios);
			
			// Agregamos la lista de usuarios a la vista
			mav.addObject("listaUsuarios", listaUsuarios);
			
			return mav;
	}
	
	// Definimos el metodo con las operaciones a realizar con /remover/usuario/{usuarioId},
	//   que es remover al usuario con el identificador usuarioId
	@GetMapping("/remover/usuario/{usuarioId}")
	public ModelAndView removerUsuario(
			@PathVariable(name="usuarioId") int usuarioId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			
			// Obtenemos la instancia de la base de datos
			Usuario usuarioARemover = usuarioService.getUsuario(usuarioId);
			
			// Llamamos el servicio para eliminar al usuario
			usuarioService.eliminar(usuarioARemover);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/listarusuarios");
			return view;
	}
	
	// Definimos el metodo con las operaciones a realizar con /modificar/usuario/{usuarioId},
	//   que es mostrar la vista para modificar usuario con la informacion del usuario del identificador usuarioId
	@GetMapping("/modificar/usuario/{usuarioId}")
	public ModelAndView modificarUsuario(
			@PathVariable(name="usuarioId") int usuarioId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
		
			// Obtenemos la instancia de la base de datos
			Usuario usuarioAModificar = usuarioService.getUsuario(usuarioId);
			
			// Agregamos el formulario a la vista de modificar usuario 
			ModelAndView mav = new ModelAndView("modificaUsuario", "formUsuarioInfo", new UsuarioInfoForm());
			
			// Agregamos el usuario en sesion
			mav.addObject("usuarioFirmado", usuarioEnSesion);  
			
			// Agregamos el id del usuario a modificar
			mav.addObject("usuarioID", usuarioAModificar.getId());	
			
			// Agregamos la instancia del usuario a modificar
			mav.addObject("usuario", usuarioAModificar);	
			
			// Agregamos la ruta para los resources
			mav.addObject("rutaResources", "../../");
			
			return mav;
	}
	
	// Definimos el metodo con las operaciones a realizar con /modificarUsuario,
	//   que es modificar el usuario con la informacion que fue enviada en el formulario
	@RequestMapping( value = "/modificarUsuario", method = RequestMethod.POST )
	public ModelAndView editarInfoUsuario(@Valid @ModelAttribute("formUsuarioInfo") UsuarioInfoForm usuarioform,
			BindingResult resultado, // Resultado de la validación 
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			ModelAndView view //  modelo a regresar
		) {
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma presentó errores");
			view.setViewName("modificaUsuario");
			
			// Obtenemos la instancia de la base de datos
			Usuario usuarioAModificar = usuarioService.getUsuario(usuarioform.getId());
			
			// Agregamos la ruta para los resources
			view.addObject("rutaResources", "");
			
			// Agregamos el usuario en Sesion
			view.addObject("usuarioFirmado", usuarioEnSesion); 
			
			// Agregamos el id del usuario a modificar
			view.addObject("usuarioID", usuarioAModificar.getId());	
			
			// Agregamos la instancia del usuario a modificar
			// view.addObject("usuario", usuarioAModificar);
			
			return view;
		}
		
		System.out.println("id a modificar: " + usuarioform.getId());
		
		// Obtenemos la instancia del usuario a modificar
		Usuario aModificar = usuarioService.getUsuario(usuarioform.getId());
		
		// Validamos si esta cambiando el username, y este ya existe
		// Obtener username actual y si es distinto lo esta cambiando
		// Checar si ya existe ese username
		if (aModificar.getUsername().equals(usuarioform.getUsername()) == false) {
			Usuario validateUsuario = usuarioService.getUsuario(usuarioform.getUsername());
			if (validateUsuario != null) {
				
				System.err.println("El username ya esta en uso");
				view.setViewName("modificaUsuario");
				
				// Obtenemos la instancia de la base de datos
				Usuario usuarioAModificar = usuarioService.getUsuario(usuarioform.getId());
				
				// Agregamos la ruta para los resources
				view.addObject("rutaResources", "");
				
				// Agregamos el usuario en Sesion
				view.addObject("usuarioFirmado", usuarioEnSesion); 
				
				// Agregamos el id del usuario a modificar
				view.addObject("usuarioID", usuarioAModificar.getId());	
				
				// Agregamos la instancia del usuario a modificar
				// view.addObject("usuario", usuarioAModificar);
				
				// Agregamos el error
				view.addObject("error", "Username ocupado");
				
				return view;
				
				
			}
		}
		
		// Desalojamos la instancia del usuario a modificar
		usuarioService.desalojar(aModificar);
		
		// Actualizamos los atributos del usuario
		aModificar.setNombre(usuarioform.getNombre());
		aModificar.setApellidos(usuarioform.getApellidos());
		aModificar.setUsername(usuarioform.getUsername());
		aModificar.setPassword(usuarioform.getPassword());
		aModificar.setCorreo( usuarioform.getCorreo());
		aModificar.setTelefono(usuarioform.getTelefono());
		
		// Actualizamos la instancia en la base de datos
		usuarioService.actualizar(aModificar);
		 
		view.setViewName("redirect:/listarusuarios");
		view.addObject("usuarioFirmado", usuarioEnSesion); 
		return view;
	}
	
	// Definimos el metodo con las operaciones a realizar con /nuevousuario,
	//   que es mostrar el formulario para agregar un nuevo usuario
	@GetMapping("/nuevousuario")
	public ModelAndView nuevoUsuario(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
		
		// Verificamos que el usuario en sesion es un administrador
		if (usuarioEnSesion.getRol().getId() == 2) { // En caso de ser socio lo redireccionamos
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/");
			view.addObject("usuarioFirmado", usuarioEnSesion);
			return view;
		}
		
		System.out.println("Mostrando Nuevo Usuario");
		
		// Mostramos la vista y agregamos el formulario necesario
		ModelAndView mav = new ModelAndView("nuevoUsuario", "formUsuarioInfo", new UsuarioInfoForm());
		  
		// Agregamos al objeto de usuario en sesion
		mav.addObject("usuarioFirmado", usuarioEnSesion);
		  
		return mav;
	}
	
	// Definimos el metodo con las operaciones a realizar con /registrarusuario,
	//  que es agregar un nuevo usuario con la informacion que fue enviada en el formulario
	@RequestMapping( value = "/registrarusuario", method = RequestMethod.POST )
	public ModelAndView registrarUsuario(
			@Valid @ModelAttribute("formUsuarioInfo") UsuarioInfoForm usuarioForm,
			BindingResult resultado, // Resultado de la validación 
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			ModelAndView view //  modelo a regresar
		) {
		
		// Verificamos que el usuario en sesion es un administrador
		if (usuarioEnSesion.getRol().getId() == 2) { // En caso de ser socio lo redirigimos
			view.setViewName("redirect:/");
			view.addObject("usuarioFirmado", usuarioEnSesion);
			return view;
		}
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma presentó errores");
			view.setViewName("nuevoUsuario");
			
			// Agregamos el usuario en Sesion
			view.addObject("usuarioFirmado", usuarioEnSesion); 
			
			return view;
		}
		
		// Validamos si esta cambiando el username, y este ya existe
		// Obtener username actual y si es distinto lo esta cambiando
		// Checar si ya existe ese username
		Usuario validateUsuario = usuarioService.getUsuario(usuarioForm.getUsername());
		if (validateUsuario != null) {
			
			System.err.println("El username ya esta en uso");
			view.setViewName("nuevoUsuario");
			
			// Agregamos el usuario en Sesion
			view.addObject("usuarioFirmado", usuarioEnSesion); 
			
			// Agregamos el error
			view.addObject("error", "Username ocupado");
			
			return view;
		}
		
		//Obtenemos el idRol
		Rol rolNuevoUsuario = rolService.getRol(usuarioForm.getIdrol());
		
		// Creamos el objeto del nuevo usuario
		Usuario nuevoUsuario = new Usuario(
				usuarioForm.getNombre(),
				usuarioForm.getApellidos(),
				usuarioForm.getUsername(),
				usuarioForm.getPassword(),
				usuarioForm.getCorreo(),
				usuarioForm.getTelefono(),
				rolNuevoUsuario
				);
		
		// Guardamos el usuario en la base de datos
		usuarioService.guardar(nuevoUsuario);
		
		view.setViewName("redirect:/listarusuarios");
		view.addObject("usuarioFirmado", usuarioEnSesion);
		return view;
	}
	
}