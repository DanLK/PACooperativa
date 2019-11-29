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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import mx.unam.pa.equipo4.cooperativa.formas.PedidoCodificadoForm;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.Producto;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.PedidoService;
import mx.unam.pa.equipo4.cooperativa.service.PedidoStatusService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoPedidoService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoService;

@Controller
public class PedidosPagadosController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	PedidoStatusService pedidoStatusService;
	
	@Autowired
	ProductoPedidoService productoPedidoService;
	
	@GetMapping("/pedidospagados")
	public ModelAndView pedidosSemana(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
			System.out.println("Mostrando Pedidos Pagados");
			ModelAndView mav = new ModelAndView("pedidosPagados");
			
			// Agregamos al objeto de usuario en sesion
			mav.addObject("usuarioFirmado", usuarioEnSesion);
			  
			// Solicitamos a la base de datos los productos disponibles
			List<Pedido> listaPedidos = pedidoService.listarPedidosPagados();
			Collections.reverse(listaPedidos);
			HashMap<Integer, List<ProductoPedido>> productosEnPedidos = new HashMap<Integer, List<ProductoPedido>>();
			for (Pedido pedido : listaPedidos) {
				List<ProductoPedido> productosEnPedido = productoPedidoService.listarProductoPedidosDePedido(pedido);
				productosEnPedidos.put(pedido.getId(), productosEnPedido);
			}
			  
			mav.addObject("listaPedidos", listaPedidos);
			mav.addObject("productosEnPedidos", productosEnPedidos);
			return mav;
	}
	
	@GetMapping("/modificar/pedidopagado/{pedidoId}")
	public ModelAndView modificarPedido(
			@PathVariable(name="pedidoId") int pedidoId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			System.out.println("Dentro de modificarPedidoID()");
			System.out.println("ID de pedido: " + pedidoId);
		
			  
			// Solicitamos a la base de datos los productos disponibles
			Pedido pedidoAModificar = pedidoService.getPedido(pedidoId);
			
	/*		// Verificamos que el usuario sea el que hizo el pedido
			if (pedidoAModificar.getUsuario().getId() != usuarioEnSesion.getId()) {
				ModelAndView view = new ModelAndView("pedidosSemana");
				view.addObject("usuarioFirmado", usuarioEnSesion);
				return view;
			}
		*/	
			// Agregamos al objeto de usuario en sesion
			ModelAndView mav = new ModelAndView("modificaMiPedido","pedidoCodificado", new PedidoCodificadoForm());
			mav.addObject("usuarioFirmado", usuarioEnSesion);
			List<ProductoPedido> productosEnPedido = productoPedidoService.listarProductoPedidosDePedido(pedidoAModificar);  
			
			// Iteramos sobre los objetos
			JSONObject productosEnPedidoCodificados = new JSONObject();
			for (int i = 0; i < productosEnPedido.size(); ++i) {
				JSONObject o1 = new JSONObject();
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
				productosEnPedidoCodificados.put(""+productosEnPedido.get(i).getProducto().getId(), o1);
			}
			
			mav.addObject("pedidoID", pedidoAModificar.getId());
			mav.addObject("productosEnPedido", productosEnPedidoCodificados.toString());
			
			// Solicitamos a la base de datos los productos disponibles
			List<Producto> listaProductos = productoService.listarProductos();
  
			mav.addObject("listaProductos", listaProductos);
			
			return mav;
	}
	
	@RequestMapping( value = "/modificarpedidopagado", method = RequestMethod.POST )
	public ModelAndView registrarPedido(
			@Valid @ModelAttribute("pedidoCodificado") PedidoCodificadoForm pedidoCodificado,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			BindingResult resultado, // Resultado de la validación 
			ModelAndView view //  modelo a regresar
		) {
		
		System.out.println(pedidoCodificado);
		
		String[] pedidoCodeString = pedidoCodificado.getPedidoCodigo().split("#");
		int pedidoID = Integer.parseInt(pedidoCodeString[0]);
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma de Pedido presentó errores");
			view.setViewName("/modificar/"+pedidoCodeString[0]);
			return view;
		}
		
		Pedido pedidoParaProducto = pedidoService.getPedido(pedidoID);
		pedidoService.desalojar(pedidoParaProducto);
		pedidoParaProducto.setTotal(pedidoCodificado.getPedidoTotal());
		pedidoService.actualizar(pedidoParaProducto);
		
		// Eliminamos los pedidoProductos que teniaPedidoStatus pedidoStatusNuevo = pedidoStatusService.getPedidoStatus(1);
		List<ProductoPedido> productosQueTenia = productoPedidoService.listarProductoPedidosDePedido(pedidoParaProducto);
		for (int i = 0; i < productosQueTenia.size(); ++i) {
			productoPedidoService.eliminar(productosQueTenia.get(i));
		}
		
		// Agregamos los nuevos
		String[] pedidoItemsString = pedidoCodeString[1].split(";");
		for (int i = 0; i < pedidoItemsString.length; ++i) {
			
			String[] pedidoItemString = pedidoItemsString[i].split(",");
			
			int idItem = Integer.parseInt(pedidoItemString[0]);
			int cantidad = Integer.parseInt(pedidoItemString[1]);
			
			Producto productoParaPedido = productoService.getProducto(idItem);
			ProductoPedido nuevoProductoPedido = new ProductoPedido(cantidad, pedidoParaProducto, productoParaPedido);
			productoPedidoService.guardar(nuevoProductoPedido);
			
		}
		
		view.setViewName("redirect:/pedidospagados");
		return view;
	}
	
	@GetMapping("/remover/pedidopagado/{pedidoId}")
	public ModelAndView removerPedido(
			@PathVariable(name="pedidoId") int pedidoId,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
					Usuario usuarioEnSesion
			) {
			System.out.println("Dentro de modificarPedidoID()");
			System.out.println("ID de pedido: " + pedidoId);
			
			Pedido pedidoARemover = pedidoService.getPedido(pedidoId);
			pedidoService.eliminar(pedidoARemover);
			
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/pedidospagados");
			return view;
	}
	
}
