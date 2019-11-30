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
			  
			// Solicitamos a la base de datos los productos disponibles
			List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
			Collections.reverse(listaUsuarios);
		
			  
			mav.addObject("listaUsuarios", listaUsuarios);
			return mav;
	}
	
	@GetMapping("/remover/usuario/{usuarioId}")
	public ModelAndView removerPedido(
			@PathVariable(name="usuarioId") int usuarioId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			// System.out.println("Dentro de modificarPedidoID()");
			// System.out.println("ID de pedido: " + pedidoId);
			
			Usuario usuarioARemover = usuarioService.getUsuario(usuarioId);
			usuarioService.eliminar(usuarioARemover);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/listarusuarios");
			return view;
	}
	
	@GetMapping("/modificar/usuario/{usuarioId}")
	public ModelAndView modificarUsuario(
			@PathVariable(name="usuarioId") int usuarioId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			Usuario usuarioAModificar = usuarioService.getUsuario(usuarioId);
			
			
			if (usuarioAModificar.getId() == usuarioEnSesion.getId()) {
				ModelAndView view = new ModelAndView("listarUsuarios");
				view.addObject("usuarioFirmado", usuarioEnSesion);
				return view;
			}
			
			// Agregamos al objeto de usuario en sesion
			ModelAndView mav = new ModelAndView("modificaUsuario", "formUsuarioInfo", new UsuarioInfoForm());
			mav.addObject("usuarioFirmado", usuarioEnSesion);  
			
			/*JSONObject o1 = new JSONObject();
			o1.put("id", new Integer(usuarioAModificar.getId()));
			o1.put("nombre", new String(usuarioAModificar.getNombre()));
			o1.put("apellidos", new String(usuarioAModificar.getApellidos()));
			o1.put("username", new String(usuarioAModificar.getUsername()));
			o1.put("correo", new String(usuarioAModificar.getCorreo()));
			o1.put("telefono", new String(usuarioAModificar.getTelefono()));*/
			
			
			//mav.addObject("usuarioMod", o1.toString());
			mav.addObject("usuarioID", usuarioAModificar.getId());	
			mav.addObject("usuario", usuarioAModificar);	
			return mav;
	}
	
	@RequestMapping( value = "/modificarUsuario", method = RequestMethod.POST )
	public ModelAndView editarInfoUsuario(@Valid @ModelAttribute("formUsuarioInfo") UsuarioInfoForm usuarioform,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			BindingResult resultado, // Resultado de la validación 
			ModelAndView view //  modelo a regresar
		) {
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma presentó errores");
			view.setViewName("listarUsuarios");
			view.addObject("usuarioFirmado", usuarioEnSesion); 
			return view;
		}
		
		System.out.println("id a modificar: " + usuarioform.getId());
		
		Usuario aModificar = usuarioService.getUsuario(usuarioform.getId());
		
		usuarioService.desalojar(aModificar);
		
		aModificar.setNombre(usuarioform.getNombre());
		aModificar.setApellidos(usuarioform.getApellidos());
		aModificar.setUsername(usuarioform.getUsername());
		aModificar.setPassword(usuarioform.getPassword());
		aModificar.setCorreo( usuarioform.getCorreo());
		aModificar.setTelefono(usuarioform.getTelefono());
		
		usuarioService.actualizar(aModificar);
		view.setViewName("redirect:/listarusuarios");
		view.addObject("usuarioFirmado", usuarioEnSesion); 
		return view;
	}
	
	
	@GetMapping("/nuevousuario")
	public ModelAndView nuevoUsuario(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
		
		// Verificamos que el usuario es un administrador
		if (usuarioEnSesion.getRol().getId() == 2) {
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/");
			view.addObject("usuarioFirmado", usuarioEnSesion);
			return view;
		}
		
		System.out.println("Mostrando Nuevo Usuario");
		ModelAndView mav = new ModelAndView("nuevoUsuario", "formUsuarioInfo", new UsuarioInfoForm());
		  
		// Agregamos al objeto de usuario en sesion
		mav.addObject("usuarioFirmado", usuarioEnSesion);
		  
		return mav;
	}
	
	@RequestMapping( value = "/registrarusuario", method = RequestMethod.POST )
	public ModelAndView registrarUsuario(
			@Valid @ModelAttribute("formUsuarioInfo") UsuarioInfoForm usuarioForm,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			BindingResult resultado, // Resultado de la validación 
			ModelAndView view //  modelo a regresar
		) {
		
		// Verificamos que el usuario es un administrador
		if (usuarioEnSesion.getRol().getId() == 2) {
			view.setViewName("redirect:/");
			view.addObject("usuarioFirmado", usuarioEnSesion);
			return view;
		}
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma de Pedido presentó errores");
			view.setViewName("nuevousuario");
			view.addObject("usuarioFirmado", usuarioEnSesion);
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
		
		usuarioService.guardar(nuevoUsuario);
		
		view.setViewName("redirect:/listarusuarios");
		view.addObject("usuarioFirmado", usuarioEnSesion);
		return view;
	}
	
	
	
}