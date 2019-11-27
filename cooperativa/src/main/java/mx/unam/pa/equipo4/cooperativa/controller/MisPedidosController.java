package mx.unam.pa.equipo4.cooperativa.controller;

import java.util.Collections;
import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.PedidoService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoPedidoService;

@Controller
public class MisPedidosController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProductoPedidoService productoPedidoService;
	
	@GetMapping("/mispedidos")
	public ModelAndView misPedidos(
			  	@SessionAttribute(
				name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
				required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
				Usuario usuarioEnSesion
		) {
		  System.out.println("Mostrando Nuevo Pedido");
		  ModelAndView mav = new ModelAndView("misPedidos");
		  
		  // Agregamos al objeto de usuario en sesion
		  mav.addObject("usuarioFirmado", usuarioEnSesion);
		  
		  // Solicitamos a la base de datos los productos disponibles
		  List<Pedido> listaPedidos = pedidoService.listarPedidosUsuario(usuarioEnSesion);
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
}
