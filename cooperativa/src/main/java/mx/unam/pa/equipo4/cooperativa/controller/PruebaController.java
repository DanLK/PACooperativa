package mx.unam.pa.equipo4.cooperativa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;
import mx.unam.pa.equipo4.cooperativa.model.Producto;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;
import mx.unam.pa.equipo4.cooperativa.model.Rol;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.PedidoService;
import mx.unam.pa.equipo4.cooperativa.service.PedidoStatusService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoPedidoService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoService;
import mx.unam.pa.equipo4.cooperativa.service.RolService;
import mx.unam.pa.equipo4.cooperativa.service.UsuarioService;

@Controller
public class PruebaController {
  @Autowired
  UsuarioService servicioUsuario;
  
  @Autowired
  RolService servicioRol;
  
  @Autowired
  ProductoService servicioProducto;
  
  @Autowired
  ProductoPedidoService servicioProductoPedido;
  
  @Autowired
  PedidoStatusService servicioPedidoStatus;
  
  @Autowired
  PedidoService servicioPedido;
  
  @GetMapping("/prueba")
  public ModelAndView mostrarPrueba(
			@SessionAttribute(
					name = "usuarioFirmado", // nombre del objeto puesto en sesión desde el controlador LoginController
					required = false) // Si no se indica esta bandera, se lanzará una excepción si dicho atributo no está en la sesión
			Usuario usuarioEnSesion
		) {
	  	System.out.println("Objeto recuperado de la sesión de usuario: " + usuarioEnSesion);
	  	
	  	
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("****************************************************************");
		System.out.println("TABLA USUARIO");
		System.out.println("****************************************************************");
		System.out.println(" ");
		System.out.println("// LISTAR ULTIMOS 10 USUARIOS ----------------------------------");
		this.printLastUsuarios(10);
		System.out.println(" ");
		System.out.println("// MODIFICAR EL ULTIMO USUARIO ----------------------------------");
		List<Usuario> listadoUltimoUsuario = servicioUsuario.listarUltimosUsuarios(1);
		Usuario usuario = listadoUltimoUsuario.get(0);
		System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
		System.out.println(usuario);
		System.out.println("... MODIFICAMOS");		
		this.modifyNameLastUsuario(usuario, "NOMBRE MODIFICADO");
		System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
		this.printLastUsuarios(1);
		System.out.println("... REVERTIMOS MODIFICACION");
		this.modifyNameLastUsuario(usuario, "NOMBRE NO MODIFICADO");
		System.out.println(" ");
		System.out.println("// AÑADIR USUARIO NUEVO ----------------------------------");
		System.out.println("... AGREGAMOS USUARIO NUEVO ");
		Rol rolSocio = servicioRol.getRol(2);
		Usuario nuevoUsuario = new Usuario("nuevo usuario", "agregado", "newUser", "newPass", "new@correo.com", null, rolSocio);
		servicioUsuario.guardar(nuevoUsuario);
		System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS ");
		this.printLastUsuarios(2);
		System.out.println(" ");
		System.out.println("// ELIMINAR ULTIMO USUARIO ---------------------------------");
		listadoUltimoUsuario = servicioUsuario.listarUltimosUsuarios(1);
		usuario = listadoUltimoUsuario.get(0);
		System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS ANTES DE ELIMINAR");
		this.printLastUsuarios(2);
		System.out.println("... ELIMININAMOS ULTIMO USUARIO");
		servicioUsuario.eliminar(usuario);
		System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS DESPUES DE ELIMINAR");
		this.printLastUsuarios(2);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("****************************************************************");
		System.out.println("TABLA ROL");
		System.out.println("****************************************************************");
		System.out.println(" ");
		System.out.println("// LISTAR ULTIMOS 3 ROLES ----------------------------------");
		this.printLastRoles(3);
		System.out.println(" ");
		System.out.println("// MODIFICAR EL ULTIMO ROL ----------------------------------");
		List<Rol> listadoUltimoRol = servicioRol.listarUltimosRoles(1);
		Rol rol = listadoUltimoRol.get(0);
		System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
		System.out.println(rol);
		System.out.println("... MODIFICAMOS");		
		this.modifyDescriptionLastRol(rol, "DESCRIPCION MODIFICADA");
		System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
		this.printLastRoles(1);
		System.out.println("... REVERTIMOS MODIFICACION");
		this.modifyDescriptionLastRol(rol, "DESCRIPCION NO MODIFICADA");
		System.out.println(" ");
		System.out.println("// AÑADIR ROL NUEVO ----------------------------------");
		System.out.println("... AGREGAMOS ROL NUEVO ");
		Rol nuevoRol = new Rol("ROL NUEVO");
		servicioRol.guardar(nuevoRol);
		System.out.println("... LISTAMOS ULTIMOS 2 ROLES ");
		this.printLastRoles(2);
		System.out.println(" ");
		System.out.println("// ELIMINAR ULTIMO ROL ---------------------------------");
		listadoUltimoRol = servicioRol.listarUltimosRoles(1);
		rol = listadoUltimoRol.get(0);
		System.out.println("... LISTAMOS ULTIMOS 2 ROLES ANTES DE ELIMINAR");
		this.printLastRoles(2);
		System.out.println("... ELIMININAMOS ULTIMO Rol");
		servicioRol.eliminar(rol);
		System.out.println("... LISTAMOS ULTIMOS 2 ROLES DESPUES DE ELIMINAR");
		this.printLastRoles(2);
			
			
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("****************************************************************");
		System.out.println("TABLA PEDIDO_STATUS");
		System.out.println("****************************************************************");
		System.out.println(" ");
		System.out.println("// LISTAR ULTIMOS 3 PEDIDO STATUS ----------------------------------");
		this.printLastPedidoStatus(3);
		System.out.println(" ");
		System.out.println("// MODIFICAR EL ULTIMO PEDIDO STATUS ----------------------------------");
		List<PedidoStatus> listadoUltimoPedidoStatus = servicioPedidoStatus.listarUltimosPedidoStatus(1);
		PedidoStatus pedidoStatus = listadoUltimoPedidoStatus.get(0);
		System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
		System.out.println(pedidoStatus);
		System.out.println("... MODIFICAMOS");		
		this.modifyDescriptionLastPedidoStatus(pedidoStatus, "DESCRIPCION MODIFICADA");
		System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
		this.printLastPedidoStatus(1);
		System.out.println("... REVERTIMOS MODIFICACION");
		this.modifyDescriptionLastPedidoStatus(pedidoStatus, "DESCRIPCION NO MODIFICADA");
		System.out.println(" ");
		System.out.println("// AÑADIR PEDIDO STATUS NUEVO ----------------------------------");
		System.out.println("... AGREGAMOS PEDIDO STATUS NUEVO ");
		PedidoStatus nuevoPedidoStatus = new PedidoStatus("PEDIDO STATUS NUEVO");
		servicioPedidoStatus.guardar(nuevoPedidoStatus);
		System.out.println("... LISTAMOS ULTIMOS 2 PEDIDO STATUS ");
		this.printLastPedidoStatus(2);
		System.out.println(" ");
		System.out.println("// ELIMINAR ULTIMO PEDIDO STATUS ---------------------------------");
		listadoUltimoPedidoStatus = servicioPedidoStatus.listarUltimosPedidoStatus(1);
		pedidoStatus = listadoUltimoPedidoStatus.get(0);
		System.out.println("... LISTAMOS ULTIMOS 2 PEDIDO STATUS ANTES DE ELIMINAR");
		this.printLastPedidoStatus(2);
		System.out.println("... ELIMININAMOS ULTIMO PedidoStatus");
		servicioPedidoStatus.eliminar(pedidoStatus);
		System.out.println("... LISTAMOS ULTIMOS 2 PEDIDO STATUS DESPUES DE ELIMINAR");
		this.printLastPedidoStatus(2);System.out.println(" ");
		
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("****************************************************************");
		System.out.println("TABLA PEDIDOS");
		System.out.println("****************************************************************");
		System.out.println(" ");
		System.out.println("// PEDIDOS DE LA ULTIMA SEMANA");
		this.printPedidosSemana();
		System.out.println("// LISTAR ULTIMOS 3 PEDIDOS ----------------------------------");
		this.printLastPedidos(3);
		System.out.println(" ");
		System.out.println("// MODIFICAR EL ULTIMO PEDIDO ----------------------------------");
		List<Pedido> listadoUltimoPedido = servicioPedido.listarUltimosPedidos(1);
		Pedido pedido = listadoUltimoPedido.get(0);
		System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
		System.out.println(pedido);
		System.out.println("... MODIFICAMOS");		
		this.modifyTotalLastPedido(pedido, Float.parseFloat("777.777"));
		System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
		this.printLastPedidos(1);
		System.out.println("... REVERTIMOS MODIFICACION");
		this.modifyTotalLastPedido(pedido, Float.parseFloat("888.888"));
		System.out.println(" ");
		System.out.println("// AÑADIR PEDIDO NUEVO ----------------------------------");
		System.out.println("... AGREGAMOS PEDIDO NUEVO ");
		PedidoStatus pedidoStatusNuevo = servicioPedidoStatus.getPedidoStatus(1);
		Usuario pedidoUsuario8 = servicioUsuario.getUsuario(8);
		Pedido nuevoPedido = new Pedido(new Date(), Float.parseFloat("555.555"), pedidoStatusNuevo, pedidoUsuario8);
		servicioPedido.guardar(nuevoPedido);
		System.out.println("... LISTAMOS ULTIMOS 2 PEDIDOS ");
		this.printLastPedidos(2);
		System.out.println(" ");
		System.out.println("// ELIMINAR ULTIMO PEDIDOS ---------------------------------");
		listadoUltimoPedido = servicioPedido.listarUltimosPedidos(1);
		pedido = listadoUltimoPedido.get(0);
		System.out.println("... LISTAMOS ULTIMOS 2 PEDIDOS ANTES DE ELIMINAR");
		this.printLastPedidos(2);
		System.out.println("... ELIMININAMOS ULTIMO Pedido");
		servicioPedido.eliminar(pedido);
		System.out.println("... LISTAMOS ULTIMOS 2 PEDIDOS DESPUES DE ELIMINAR");
		this.printLastPedidos(2);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("****************************************************************");
		System.out.println("TABLA PRODUCTO");
		System.out.println("****************************************************************");
		System.out.println(" ");
		System.out.println("// LISTAR ULTIMOS 3 PRODUCTOS ----------------------------------");
		this.printLastProductos(3);
		System.out.println(" ");
		System.out.println("// MODIFICAR EL ULTIMO PRODUCTO ----------------------------------");
		List<Producto> listadoUltimoProducto = servicioProducto.listarUltimosProductos(1);
		Producto producto = listadoUltimoProducto.get(0);
		System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
		System.out.println(producto);
		System.out.println("... MODIFICAMOS");		
		this.modifyNombreLastProducto(producto, "NOMBRE MODIFICADO");
		System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
		this.printLastProductos(1);
		System.out.println("... REVERTIMOS MODIFICACION");
		this.modifyNombreLastProducto(producto, "NOMBRE NO MODIFICADO");
		System.out.println(" ");
		System.out.println("// AÑADIR PRODUCTO NUEVO ----------------------------------");
		System.out.println("... AGREGAMOS PRODUCTO NUEVO ");
		Producto nuevoProducto = new Producto("PRODUCTO NUEVO", "3 kg", Float.parseFloat("555.555"), "Frutas y verduras");
		servicioProducto.guardar(nuevoProducto);
		System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTOS ");
		this.printLastProductos(2);
		System.out.println(" ");
		System.out.println("// ELIMINAR ULTIMO PRODUCTO ---------------------------------");
		listadoUltimoProducto = servicioProducto.listarUltimosProductos(1);
		producto = listadoUltimoProducto.get(0);
		System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTO ANTES DE ELIMINAR");
		this.printLastProductos(2);
		System.out.println("... ELIMININAMOS ULTIMO Producto");
		servicioProducto.eliminar(producto);
		System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTO DESPUES DE ELIMINAR");
		this.printLastProductos(2);
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("****************************************************************");
		System.out.println("TABLA PRODUCTO PEDIDOS");
		System.out.println("****************************************************************");
		System.out.println(" ");
		System.out.println("// LISTAR ULTIMOS 3 PRODUCTO PEDIDOS ----------------------------------");
		this.printLastProductoPedidos(3);
		System.out.println(" ");
		System.out.println("// MODIFICAR EL ULTIMO PRODUCTO PEDIDO ----------------------------------");
		List<ProductoPedido> listadoUltimoProductoPedido = servicioProductoPedido.listarUltimosProductoPedidos(1);
		ProductoPedido productoPedido = listadoUltimoProductoPedido.get(0);
		System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
		System.out.println(productoPedido);
		System.out.println("... MODIFICAMOS");		
		this.modifyCantidadLastProductoPedido(productoPedido, 777);
		System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
		this.printLastProductoPedidos(1);
		System.out.println("... REVERTIMOS MODIFICACION");
		this.modifyCantidadLastProductoPedido(productoPedido, 888);
		System.out.println(" ");
		System.out.println("// AÑADIR PRODUCTO PEDIDO NUEVO ----------------------------------");
		System.out.println("... AGREGAMOS PRODUCTO PEDIDO NUEVO ");
		Pedido pedidoParaProducto = servicioPedido.getPedido(3);
		Producto productoParaPedido = servicioProducto.getProducto(13);
		ProductoPedido nuevoProductoPedido = new ProductoPedido(2, pedidoParaProducto, productoParaPedido);
		servicioProductoPedido.guardar(nuevoProductoPedido);
		System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTO PEDIDOS ");
		this.printLastProductoPedidos(2);
		System.out.println(" ");
		System.out.println("// ELIMINAR ULTIMO PRODUCTO PEDIDOS ---------------------------------");
		listadoUltimoProductoPedido = servicioProductoPedido.listarUltimosProductoPedidos(1);
		productoPedido = listadoUltimoProductoPedido.get(0);
		System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTO PEDIDOS ANTES DE ELIMINAR");
		this.printLastProductoPedidos(2);
		System.out.println("... ELIMININAMOS ULTIMO ProductoPedido");
		servicioProductoPedido.eliminar(productoPedido);
		System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTO PEDIDOS DESPUES DE ELIMINAR");
		this.printLastProductoPedidos(2);
		
		
		ModelAndView view = new ModelAndView("welcome");
		view.addObject("usuarioFirmado", usuarioEnSesion); // Creación de la sesión de usuario
		return view;

	}
	
	public void modifyCantidadLastProductoPedido(ProductoPedido productoPedido, int newCantidad) {
		servicioProductoPedido.desalojar(productoPedido);
		productoPedido.setCantidad(newCantidad);
		servicioProductoPedido.actualizar(productoPedido);
	}
	
	public void printLastProductoPedidos(int many) {
		List<ProductoPedido> listadoUltimosProductoPedidos = servicioProductoPedido.listarUltimosProductoPedidos(many);
		this.printProductoPedidos(listadoUltimosProductoPedidos);
	}
	
	public void printProductoPedidos(List<ProductoPedido> productoPedidos) {
		for(ProductoPedido productoPedido: productoPedidos) {
			System.out.println(productoPedido);
		}
	}
	
	public void modifyNombreLastProducto(Producto producto, String newNombre) {
		servicioProducto.desalojar(producto);
		producto.setNombre(newNombre);
		servicioProducto.actualizar(producto);
	}
	
	public void printLastProductos(int many) {
		List<Producto> listadoUltimosProductos = servicioProducto.listarUltimosProductos(many);
		this.printProductos(listadoUltimosProductos);
	}
	
	public void printProductos(List<Producto> productos) {
		for(Producto producto: productos) {
			System.out.println(producto);
		}
	}
	
	public void printLastPedidos(int many) {
		List<Pedido> listadoUltimosPedidos = servicioPedido.listarUltimosPedidos(many);
		this.printPedidos(listadoUltimosPedidos);
	}
	
	public void printPedidos(List<Pedido> pedidos) {
		for(Pedido pedido: pedidos) {
			System.out.println(pedido);
		}
	}
	
	public void modifyTotalLastPedido(Pedido pedido, float newTotal) {
		servicioPedido.desalojar(pedido);
		pedido.setTotal(newTotal);
		servicioPedido.actualizar(pedido);
	}
	
	public void modifyDescriptionLastPedidoStatus(PedidoStatus pedidoStatus, String newDescription) {
		servicioPedidoStatus.desalojar(pedidoStatus);
		pedidoStatus.setDescripcion(newDescription);
		servicioPedidoStatus.actualizar(pedidoStatus);
	}
	
	public void printLastPedidoStatus(int many) {
		List<PedidoStatus> listadoUltimosPedidoStatus = servicioPedidoStatus.listarUltimosPedidoStatus(many);
		this.printPedidoStatus(listadoUltimosPedidoStatus);
	}
	
	public void printPedidoStatus(List<PedidoStatus> pedidoStatusList) {
		for(PedidoStatus pedidoStatus: pedidoStatusList) {
			System.out.println(pedidoStatus);
		}
	}
	
	public void printLastRoles(int many) {
		List<Rol> listadoUltimosRoles = servicioRol.listarUltimosRoles(many);
		this.printRoles(listadoUltimosRoles);
	}
	
	public void printRoles(List<Rol> roles) {
		for(Rol rol: roles) {
			System.out.println(rol);
		}
	}
	
	public void modifyDescriptionLastRol(Rol rol, String newDescription) {
		servicioRol.desalojar(rol);
		rol.setDescripcion(newDescription);
		servicioRol.actualizar(rol);
	}
	
	public void modifyNameLastUsuario(Usuario usuario, String newName) {
		servicioUsuario.desalojar(usuario);
		usuario.setNombre(newName);
		servicioUsuario.actualizar(usuario);
	}
	
	public void printLastUsuarios(int many) {
		List<Usuario> listadoUltimosUsuarios = servicioUsuario.listarUltimosUsuarios(many);
		this.printUsuarios(listadoUltimosUsuarios);
	}
		
	public void printUsuarios(List<Usuario> usuarios) {
		for(Usuario usuario: usuarios) {
			System.out.println(usuario);
		}
	}
	
	public void printPedidosSemana() {
		List<Pedido> listadoPedidosSemana = servicioPedido.listarPedidosSemana();
		this.printPedidos(listadoPedidosSemana);
	}
  
}