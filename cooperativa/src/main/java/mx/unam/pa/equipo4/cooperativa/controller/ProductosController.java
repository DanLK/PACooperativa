package mx.unam.pa.equipo4.cooperativa.controller;

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

import mx.unam.pa.equipo4.cooperativa.formas.ProductoForm;
import mx.unam.pa.equipo4.cooperativa.model.Producto;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.ProductoService;

@Controller
public class ProductosController {
	@Autowired
	ProductoService productoService;
	
	@GetMapping("/listarproductos")
	public ModelAndView listarPedidos(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
			System.out.println("Log en el controlador de productos");
			
			// Verificamos que el usuario es un administrador
			if (usuarioEnSesion.getRol().getId() == 2) {
				ModelAndView view = new ModelAndView();
				view.setViewName("redirect:/");
				return view;
			}
			
			ModelAndView mav = new ModelAndView("listarProductos");
			
			// Agregamos al objeto de usuario en sesion
			mav.addObject("usuarioFirmado", usuarioEnSesion);
			  
			// Solicitamos a la base de datos los productos disponibles
			List<Producto> listaProductos = productoService.listarProductos();
			  
			mav.addObject("listaProductos", listaProductos);
			return mav;
	}
	
	@GetMapping("/modificar/producto/{productoId}")
	public ModelAndView modificarPedido(
			@PathVariable(name="productoId") int productoId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			System.out.println("Dentro de modificarProductoID()");
			System.out.println("ID de pedido: " + productoId);
		  
			// Solicitamos a la base de datos los productos disponibles
			Producto productoAModificar = productoService.getProducto(productoId);
				
			// Creamos la vista con el formulario
			ModelAndView mav = new ModelAndView("modificarProducto","productoForm", new ProductoForm());
			
			mav.addObject("productoAModificar", productoAModificar);
			
			return mav;
	}
	
	@RequestMapping( value = "/modificarProducto", method = RequestMethod.POST )
	public ModelAndView guardarCambiosProducto(
			@Valid @ModelAttribute("productoForm") ProductoForm productoForm,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			BindingResult resultado, // Resultado de la validación 
			ModelAndView view //  modelo a regresar
		) {
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma de Producto presentó errores");
			view.setViewName("/modificar/" + productoForm.getId());
			return view;
		}
		
		Producto productoAModificar = productoService.getProducto(productoForm.getId());
		productoService.desalojar(productoAModificar);
		
		productoAModificar.setNombre(productoForm.getNombre());
		productoAModificar.setContenido(productoForm.getContenido());
		productoAModificar.setPrecio(productoForm.getPrecio());
		productoAModificar.setDepartamento(productoForm.getDepartamento());
		
		productoService.actualizar(productoAModificar);
		
		view.setViewName("redirect:/listarproductos");
		return view;
	}
	
	@GetMapping("/remover/producto/{productoId}")
	public ModelAndView removerProducto(
			@PathVariable(name="productoId") int productoId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			System.out.println("Dentro de removerProductoID()");
			System.out.println("ID de producto: " + productoId);
			
			Producto productoARemover = productoService.getProducto(productoId);
			productoService.eliminar(productoARemover);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/listarproductos");
			return view;
	}
	
	@GetMapping("/nuevoproducto")
	public ModelAndView nuevoProducto(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
		
		// Verificamos que el usuario es un administrador
		if (usuarioEnSesion.getRol().getId() == 2) {
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/");
			return view;
		}
		
		System.out.println("Mostrando Nuevo Producto");
		ModelAndView mav = new ModelAndView("nuevoProducto","productoForm", new ProductoForm());
		  
		// Agregamos al objeto de usuario en sesion
		mav.addObject("usuarioFirmado", usuarioEnSesion);
		  
		return mav;
	}
	
	@RequestMapping( value = "/registrarproducto", method = RequestMethod.POST )
	public ModelAndView registrarProducto(
			@Valid @ModelAttribute("productoForm") ProductoForm productoForm,
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
			return view;
		}
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma de Pedido presentó errores");
			view.setViewName("nuevoproducto");
			return view;
		}
			
		Producto nuevoProducto = new Producto(
				productoForm.getNombre(),
				productoForm.getContenido(),
				productoForm.getPrecio(),
				productoForm.getDepartamento()
			);
		
		productoService.guardar(nuevoProducto);
		
		view.setViewName("redirect:/listarproductos");
		return view;
	}
	
	
}
