package mx.unam.pa.equipo4.cooperativa.controller;

import java.util.Date;
import java.util.List;

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

@Controller
@SessionAttributes("usuarioFirmado")
public class NuevoPedidoController {
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	PedidoStatusService pedidoStatusService;
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProductoPedidoService productoPedidoService;
	
	@GetMapping("/nuevopedido")
	  public ModelAndView nuevoPedido(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
		  System.out.println("Mostrando Nuevo Pedido");
		  ModelAndView mav = new ModelAndView("nuevoPedido","pedidoCodificado", new PedidoCodificadoForm());
		  
		  // Agregamos al objeto de usuario en sesion
		  mav.addObject("usuarioFirmado", usuarioEnSesion);
		  
		  // Solicitamos a la base de datos los productos disponibles
		  List<Producto> listaProductos = productoService.listarProductos();
		  
		  mav.addObject("listaProductos", listaProductos);
		  return mav;
		}
	
	@RequestMapping( value = "/registrarPedido", method = RequestMethod.POST )
	public ModelAndView registrarPedido(
			@Valid @ModelAttribute("pedidoCodificado") PedidoCodificadoForm pedidoCodificado,
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion,
			BindingResult resultado, // Resultado de la validación 
			ModelAndView view //  modelo a regresar
		) {
		
		if( resultado.hasErrors() ) {
			System.err.println("La validación de la forma de Pedido presentó errores");
			view.setViewName("nuevoPedido");
			return view;
		}
		
		System.out.println(pedidoCodificado);
		
		PedidoStatus pedidoStatusNuevo = pedidoStatusService.getPedidoStatus(1);
		
		Pedido nuevoPedido = new Pedido(new Date(), pedidoCodificado.getPedidoTotal(), pedidoStatusNuevo, usuarioEnSesion);
		pedidoService.guardar(nuevoPedido);
		Pedido pedidoParaProducto = pedidoService.listarUltimosPedidos(1).get(0);
		
		String[] pedidoItemsString = pedidoCodificado.getPedidoCodigo().split(";");
		
		for (int i = 0; i < pedidoItemsString.length; ++i) {
			
			String[] pedidoItemString = pedidoItemsString[i].split(",");
			
			int idItem = Integer.parseInt(pedidoItemString[0]);
			int cantidad = Integer.parseInt(pedidoItemString[1]);
			
			Producto productoParaPedido = productoService.getProducto(idItem);
			ProductoPedido nuevoProductoPedido = new ProductoPedido(cantidad, pedidoParaProducto, productoParaPedido);
			productoPedidoService.guardar(nuevoProductoPedido);
			
		}
		
		view.setViewName("redirect:/");
		return view;
	}
	
}
