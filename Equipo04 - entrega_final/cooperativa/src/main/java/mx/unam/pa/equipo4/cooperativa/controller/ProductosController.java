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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import mx.unam.pa.equipo4.cooperativa.formas.ProductoForm;
import mx.unam.pa.equipo4.cooperativa.model.Producto;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.ProductoService;

// Clase controlador para las operaciones sobre los productos
@Controller
@SessionAttributes("usuarioFirmado")
public class ProductosController {
	@Autowired
	ProductoService productoService;
	
	// Definimos el metodo con las operaciones a realizar con /listarproductos,
	//   que es mostrar la lista de productos
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
				view.addObject("usuarioFirmado", usuarioEnSesion);
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
	
	// Definimos el metodo con las operaciones a realizar con /modificar/producto/{productoId},
	//   que es mostrar la vista de modificacion de producto con la informacion del producto con el identificador productoId
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
		  
			// Obtenemos la instancia del producto de la base de datos
			Producto productoAModificar = productoService.getProducto(productoId);
				
			// Creamos la vista con el formulario
			ModelAndView mav = new ModelAndView("modificarProducto","productoForm", new ProductoForm());
			
			// Agregamos el objeto del usuario en sesion
			mav.addObject("usuarioFirmado", usuarioEnSesion);
			
			// Agregamos el objeto del producto a modificar
			mav.addObject("productoAModificar", productoAModificar);
			
			// Agregamos la ruta de los resources
			mav.addObject("rutaResources", "../../");
			
			return mav;
	}
	
	// Definimos el metodo con las operaciones a realizar con /modificarProducto,
	//   que es guardar los cambios realizados a un producto y redirigir al usuario al listado de productos
	@RequestMapping( value = "/modificarProducto", method = RequestMethod.POST )
	public ModelAndView guardarCambiosProducto(
			@Valid @ModelAttribute("productoForm") ProductoForm productoForm,
			BindingResult resultado, // Resultado de la validación
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion, 
			ModelAndView view //  modelo a regresar
		) {
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma de Producto presentó errores");
			view.setViewName("modificarProducto");
			
			// Agregamos la ruta de los resources
			view.addObject("rutaResources", "");
			
			return view;
		}
		
		// Obtenemos la instancia de la base de datos del producto a modificar
		Producto productoAModificar = productoService.getProducto(productoForm.getId());
		
		// Desalojamos la instancia del producto a modificar
		productoService.desalojar(productoAModificar);
		
		// Actualizamos la informacion del producto a modificar
		productoAModificar.setNombre(productoForm.getNombre());
		productoAModificar.setContenido(productoForm.getContenido());
		productoAModificar.setPrecio(Float.parseFloat(productoForm.getPrecio()));
		productoAModificar.setDepartamento(productoForm.getDepartamento());
		
		// Actualizamos la instancia en la base de datos
		productoService.actualizar(productoAModificar);
		
		view.setViewName("redirect:/listarproductos");
		view.addObject("usuarioFirmado", usuarioEnSesion);
		return view;
	}
	
	// Definimos el metodo con las operaciones a realizar con /remover/producto/{productoId},
	//   que es remover el producto identificado por productoId
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
			
			// Obtenemos la instancia de la base de datos del producto a remover
			Producto productoARemover = productoService.getProducto(productoId);
			
			// Llamamos el servicio para eliminar la instancia del producto
			productoService.eliminar(productoARemover);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/listarproductos");
			view.addObject("usuarioFirmado", usuarioEnSesion);
			return view;
	}
	
	// Definimos el metodo con las operaciones a realizar con /nuevoproducto,
	//   que es mostrar el formulario para agregar un nuevo usuario
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
			view.addObject("usuarioFirmado", usuarioEnSesion);
			return view;
		}
		
		System.out.println("Mostrando Nuevo Producto");
		
		// Instanciamos la vista de nuevo producto y agregamos la forma necesaria
		ModelAndView mav = new ModelAndView("nuevoProducto","productoForm", new ProductoForm());
		  
		// Agregamos al objeto de usuario en sesion
		mav.addObject("usuarioFirmado", usuarioEnSesion);
		
		// Agregamos la ruta de los resources
		mav.addObject("rutaResources", "../../");
		
		return mav;
	}
	
	// Definimos el metodo con las operaciones a realizar con /registrarproducto,
	//  que es agregar un nuevo producto con la informacion que fue enviada en el formulario
	@RequestMapping( value = "/registrarproducto", method = RequestMethod.POST )
	public ModelAndView registrarProducto(
			@Valid @ModelAttribute("productoForm") ProductoForm productoForm,
			BindingResult resultado, // Resultado de la validación
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion, 
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
			view.setViewName("nuevoProducto");
			view.addObject("usuarioFirmado", usuarioEnSesion);
			return view;
		}
			
		// Creamos una nueva instancia de producto con la infromacion del formulario
		Producto nuevoProducto = new Producto(
				productoForm.getNombre(),
				productoForm.getContenido(),
				Float.parseFloat(productoForm.getPrecio()),
				productoForm.getDepartamento()
			);
		
		// Guardamos el producto en la base de datos
		productoService.guardar(nuevoProducto);
		
		view.setViewName("redirect:/listarproductos");
		view.addObject("usuarioFirmado", usuarioEnSesion);
		return view;
	}
	
	
}
