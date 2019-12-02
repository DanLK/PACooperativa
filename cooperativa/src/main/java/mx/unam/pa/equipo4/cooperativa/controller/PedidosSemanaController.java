package mx.unam.pa.equipo4.cooperativa.controller;

import java.util.Collections;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import mx.unam.pa.equipo4.cooperativa.formas.PedidoCodificadoForm;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;
import mx.unam.pa.equipo4.cooperativa.model.Producto;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.PedidoService;
import mx.unam.pa.equipo4.cooperativa.service.PedidoStatusService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoPedidoService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoService;

//Clase controlador para las operaciones sobre los pedidos de la semana
@Controller
public class PedidosSemanaController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	PedidoStatusService pedidoStatusService;
	
	@Autowired
	ProductoPedidoService productoPedidoService;
	
	// Definimos el metodo con las operaciones a realizar con /pedidossemana,
	//   que es mostrar todos los pedidos
	@GetMapping("/pedidossemana")
	public ModelAndView pedidosSemana(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
			System.out.println("Mostrando Pedidos Semana");
			ModelAndView mav = new ModelAndView("pedidosSemana");
			
			// Agregamos al objeto de usuario en sesion
			mav.addObject("usuarioFirmado", usuarioEnSesion);
			  
			// Solicitamos a la base de datos TODOS los pedidos
			List<Pedido> listaPedidos = pedidoService.listarPedidosSemana();
			
			// Revertimos los pedidos para mostrar los mas nuevos primeros 
			Collections.reverse(listaPedidos);
			
			// Preparamos una tabla hash para guardar una lista de productos por pedido
			HashMap<Integer, List<ProductoPedido>> productosEnPedidos = new HashMap<Integer, List<ProductoPedido>>();
			
			// Recorremos los pedidos
			for (Pedido pedido : listaPedidos) {
				
				// Solicitamos a la base de datos los productos del pedido actual
				List<ProductoPedido> productosEnPedido = productoPedidoService.listarProductoPedidosDePedido(pedido);
				
				// Guardamos la lista de productos con el identificador del pedido correspondiente
				productosEnPedidos.put(pedido.getId(), productosEnPedido);
			}
			  
			// Agregamos a la vista el objeto con la lista de pedidos
			mav.addObject("listaPedidos", listaPedidos);
			
			// Agregamos a la vista el objeto de la tabla hash con los productos por pedido
			mav.addObject("productosEnPedidos", productosEnPedidos);
			
			return mav;
	}
	
	
	// Definimos el metodo con las operaciones a realizar con /modificar/elpedido/{pedidoId},
	//   que es mostrar la vista de modificacion de pedido con la informacion del pedido con el identificador pedidoId
	@GetMapping("/modificar/elpedido/{pedidoId}")
	public ModelAndView modificarPedido(
			@PathVariable(name="pedidoId") int pedidoId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			System.out.println("Dentro de modificarPedidoID()");
			System.out.println("ID de pedido: " + pedidoId);
		
			  
			// Obtenemos el pedido a modificar
			Pedido pedidoAModificar = pedidoService.getPedido(pedidoId);
		
			// Usamos la vista modificaMiPedido y agregamos el form requerido
			ModelAndView mav = new ModelAndView("modificaMiPedido","pedidoCodificado", new PedidoCodificadoForm());
			
			// Agregamos al objeto de usuario en sesion
			mav.addObject("usuarioFirmado", usuarioEnSesion);
			
			// Obtenemos los productos del pedido de la base de datos
			List<ProductoPedido> productosEnPedido = productoPedidoService.listarProductoPedidosDePedido(pedidoAModificar);  
			
			// Codificaremos el pedido en formato JSON para su uso en la vista por Javascript
			JSONObject productosEnPedidoCodificados = new JSONObject();
			
			// Recorreremos los productos en el pedido
			for (int i = 0; i < productosEnPedido.size(); ++i) {
				
				// Inicializamos el objeto JSON para el producto actual
				JSONObject o1 = new JSONObject();
				
				// Asignamos sus atributos
				o1.put("id", new Integer(productosEnPedido.get(i).getProducto().getId()));
				o1.put("nombre", new String(productosEnPedido.get(i).getProducto().getNombre()));
				if (StringUtils.isEmpty(productosEnPedido.get(i).getProducto().getContenido())) {
					o1.put("contenido", new String());
				} else {
					o1.put("contenido", new String(productosEnPedido.get(i).getProducto().getContenido()));
				}
				o1.put("departamento", new String(productosEnPedido.get(i).getProducto().getDepartamento()));
				o1.put("precioUnitario", new Float(productosEnPedido.get(i).getProducto().getPrecio()));
				o1.put("cantidad", new Integer(productosEnPedido.get(i).getCantidad()));
				o1.put("subtotal", new Float(productosEnPedido.get(i).getProducto().getPrecio() * productosEnPedido.get(i).getCantidad()));
				
				// Agregamos el producto actual al objeto del pedido
				productosEnPedidoCodificados.put(""+productosEnPedido.get(i).getProducto().getId(), o1);
			}
			
			
			// Agregamos a la vista el identificador del pedido
			mav.addObject("pedidoID", pedidoAModificar.getId());
			
			// Agregamos a la vista el objeto JSON con los productos del pedido codificados en JSON
			mav.addObject("productosEnPedido", productosEnPedidoCodificados.toString());
			
			// Agregamos la ruta a donde se enviara el submit del form con la informacion modificada del pedido
			mav.addObject("rutaAction", "../../modificarElPedido");
			
			// Solicitamos a la base de datos los productos disponibles
			List<Producto> listaProductos = productoService.listarProductos();
  
			// Agregamos la lista de productos a la vista
			mav.addObject("listaProductos", listaProductos);
			
			return mav;
	}
	
	
	// Definimos el metodo con las operaciones a realizar con /modificarElPedido,
	//   que es guardar los cambios realizados a un pedido y redirigir al usuario al listado de pedidos
	@RequestMapping( value = "/modificarElPedido", method = RequestMethod.POST )
	public ModelAndView registrarPedido(
			@Valid @ModelAttribute("pedidoCodificado") PedidoCodificadoForm pedidoCodificado,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			BindingResult resultado, // Resultado de la validación 
			ModelAndView view //  modelo a regresar
		) {
		
		
		// Se obtiene el pedido codificado el cual esta conformado de la siguiente manera:
		// <identificador_pedido_status>$<identificador_pedido>#<codificacion_de_productos>
		// Donde a su vez <codificacion_de_productos> es de la forma:
		// <id_de_producto_1>,<cantidad_producto_1>;<id_de_producto_2>,<cantidad_producto_2>;...;<id_de_producto_n>,<cantidad_producto_n>
		System.out.println(pedidoCodificado);
		
		// Obtenemos el identificador del status que esta antes de "$" en la codificacion
		String[] pedidoCodeStatusString = pedidoCodificado.getPedidoCodigo().split("\\$");
		int pedidoStatusID = Integer.parseInt(pedidoCodeStatusString[0]);
		
		// Obtenemos el identificador del pedido que esta antes de "#" en la codificacion
		String[] pedidoCodeString = pedidoCodeStatusString[1].split("#");
		int pedidoID = Integer.parseInt(pedidoCodeString[0]);
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma de Pedido presentó errores");
			view.setViewName("/modificar/"+pedidoCodeString[0]);
			return view;
		}
		
		// Obtenemos de la base de datos el status Pedido necesario
		PedidoStatus pedidoStatusDelPedido = pedidoStatusService.getPedidoStatus(pedidoStatusID);
		
		// Obtenemos de la base de datos el pedido necesario
		Pedido pedidoParaProducto = pedidoService.getPedido(pedidoID);
		
		// Desalojamos el pedido
		pedidoService.desalojar(pedidoParaProducto);
		
		// Actualizamos el valor del total y Status del pedido modificado
		pedidoParaProducto.setTotal(pedidoCodificado.getPedidoTotal());
		pedidoParaProducto.setPedidoStatus(pedidoStatusDelPedido);
		
		// Actualizamos el pedido actual
		pedidoService.actualizar(pedidoParaProducto);
		
		// Eliminamos los pedidoProductos que tenia
		List<ProductoPedido> productosQueTenia = productoPedidoService.listarProductoPedidosDePedido(pedidoParaProducto);
		for (int i = 0; i < productosQueTenia.size(); ++i) {
			productoPedidoService.eliminar(productosQueTenia.get(i));
		}
		
		// Agregamos los nuevos
		String[] pedidoItemsString = pedidoCodeString[1].split(";");
		for (int i = 0; i < pedidoItemsString.length; ++i) {
			
			// Decodificamos los productos que estan de la forma:
			// <id_de_producto_1>,<cantidad_producto_1>;<id_de_producto_2>,<cantidad_producto_2>;...;<id_de_producto_n>,<cantidad_producto_n>
			String[] pedidoItemString = pedidoItemsString[i].split(",");
			
			int idItem = Integer.parseInt(pedidoItemString[0]);
			int cantidad = Integer.parseInt(pedidoItemString[1]);
			
			// Obtenemos el producto de la base de datos
			Producto productoParaPedido = productoService.getProducto(idItem);
			
			// Creamos una nueva instancia de producto pedido 
			ProductoPedido nuevoProductoPedido = new ProductoPedido(cantidad, pedidoParaProducto, productoParaPedido);
			
			// Guardamos la nueva instancia en la base de datos
			productoPedidoService.guardar(nuevoProductoPedido);
			
		}
		
		view.setViewName("redirect:/pedidossemana");
		return view;
	}
	
	// Definimos el metodo con las operaciones a realizar con /remover/elpedido/{pedidoId},
	//   que es remover el pedido identificado por pedidoId
	@GetMapping("/remover/elpedido/{pedidoId}")
	public ModelAndView removerPedido(
			@PathVariable(name="pedidoId") int pedidoId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			System.out.println("Dentro de modificarPedidoID()");
			System.out.println("ID de pedido: " + pedidoId);
			
			// Obtenemos el objeto de la base de datos
			Pedido pedidoARemover = pedidoService.getPedido(pedidoId);
			
			// Eliminamos la instancia de la base de datos
			pedidoService.eliminar(pedidoARemover);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/pedidossemana");
			return view;
	}
	
}