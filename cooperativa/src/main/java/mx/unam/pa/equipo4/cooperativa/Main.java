package mx.unam.pa.equipo4.cooperativa;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mx.unam.pa.equipo4.cooperativa.config.HibernateConfig;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;
import mx.unam.pa.equipo4.cooperativa.model.Producto;
import mx.unam.pa.equipo4.cooperativa.model.Rol;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.PedidoService;
import mx.unam.pa.equipo4.cooperativa.service.PedidoStatusService;
import mx.unam.pa.equipo4.cooperativa.service.ProductoService;
import mx.unam.pa.equipo4.cooperativa.service.RolService;
import mx.unam.pa.equipo4.cooperativa.service.UsuarioService;

public class Main {
	
	public static void main(String[] args) {
		Main m = new Main();
		
		try(AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(HibernateConfig.class) ){
			// Obtenemos los servicios
			UsuarioService servicioUsuario = context.getBean(UsuarioService.class);
			RolService servicioRol = context.getBean(RolService.class);
			PedidoStatusService servicioPedidoStatus = context.getBean(PedidoStatusService.class);
			PedidoService servicioPedido = context.getBean(PedidoService.class);
			ProductoService servicioProducto = context.getBean(ProductoService.class);
			
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("****************************************************************");
			System.out.println("TABLA USUARIO");
			System.out.println("****************************************************************");
			System.out.println(" ");
			System.out.println("// LISTAR ULTIMOS 10 USUARIOS ----------------------------------");
			m.printLastUsuarios(m, servicioUsuario, 10);
			System.out.println(" ");
			System.out.println("// MODIFICAR EL ULTIMO USUARIO ----------------------------------");
			List<Usuario> listadoUltimoUsuario = servicioUsuario.listarUltimosUsuarios(1);
			Usuario usuario = listadoUltimoUsuario.get(0);
			System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
			System.out.println(usuario);
			System.out.println("... MODIFICAMOS");		
			m.modifyNameLastUsuario(m, servicioUsuario, usuario, "NOMBRE MODIFICADO");
			System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
			m.printLastUsuarios(m, servicioUsuario, 1);
			System.out.println("... REVERTIMOS MODIFICACION");
			m.modifyNameLastUsuario(m, servicioUsuario, usuario, "NOMBRE NO MODIFICADO");
			System.out.println(" ");
			System.out.println("// AÑADIR USUARIO NUEVO ----------------------------------");
			System.out.println("... AGREGAMOS USUARIO NUEVO ");
			Rol rolSocio = servicioRol.getRol(2);
			Usuario nuevoUsuario = new Usuario("nuevo usuario", "agregado", "newUser", "newPass", "new@correo.com", null, rolSocio);
			servicioUsuario.guardar(nuevoUsuario);
			System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS ");
			m.printLastUsuarios(m, servicioUsuario, 2);
			System.out.println(" ");
			System.out.println("// ELIMINAR ULTIMO USUARIO ---------------------------------");
			listadoUltimoUsuario = servicioUsuario.listarUltimosUsuarios(1);
			usuario = listadoUltimoUsuario.get(0);
			System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS ANTES DE ELIMINAR");
			m.printLastUsuarios(m, servicioUsuario, 2);
			System.out.println("... ELIMININAMOS ULTIMO USUARIO");
			servicioUsuario.eliminar(usuario);
			System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS DESPUES DE ELIMINAR");
			m.printLastUsuarios(m, servicioUsuario, 2);
			
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("****************************************************************");
			System.out.println("TABLA ROL");
			System.out.println("****************************************************************");
			System.out.println(" ");
			System.out.println("// LISTAR ULTIMOS 3 ROLES ----------------------------------");
			m.printLastRoles(m, servicioRol, 3);
			System.out.println(" ");
			System.out.println("// MODIFICAR EL ULTIMO ROL ----------------------------------");
			List<Rol> listadoUltimoRol = servicioRol.listarUltimosRoles(1);
			Rol rol = listadoUltimoRol.get(0);
			System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
			System.out.println(rol);
			System.out.println("... MODIFICAMOS");		
			m.modifyDescriptionLastRol(m, servicioRol, rol, "DESCRIPCION MODIFICADA");
			System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
			m.printLastRoles(m, servicioRol, 1);
			System.out.println("... REVERTIMOS MODIFICACION");
			m.modifyDescriptionLastRol(m, servicioRol, rol, "DESCRIPCION NO MODIFICADA");
			System.out.println(" ");
			System.out.println("// AÑADIR ROL NUEVO ----------------------------------");
			System.out.println("... AGREGAMOS ROL NUEVO ");
			Rol nuevoRol = new Rol("ROL NUEVO");
			servicioRol.guardar(nuevoRol);
			System.out.println("... LISTAMOS ULTIMOS 2 ROLES ");
			m.printLastRoles(m, servicioRol, 2);
			System.out.println(" ");
			System.out.println("// ELIMINAR ULTIMO ROL ---------------------------------");
			listadoUltimoRol = servicioRol.listarUltimosRoles(1);
			rol = listadoUltimoRol.get(0);
			System.out.println("... LISTAMOS ULTIMOS 2 ROLES ANTES DE ELIMINAR");
			m.printLastRoles(m, servicioRol, 2);
			System.out.println("... ELIMININAMOS ULTIMO Rol");
			servicioRol.eliminar(rol);
			System.out.println("... LISTAMOS ULTIMOS 2 ROLES DESPUES DE ELIMINAR");
			m.printLastRoles(m, servicioRol, 2);
			
			
            System.out.println(" ");
			System.out.println(" ");
			System.out.println("****************************************************************");
			System.out.println("TABLA PEDIDO_STATUS");
			System.out.println("****************************************************************");
			System.out.println(" ");
			System.out.println("// LISTAR ULTIMOS 3 PEDIDO STATUS ----------------------------------");
			m.printLastPedidoStatus(m, servicioPedidoStatus, 3);
			System.out.println(" ");
			System.out.println("// MODIFICAR EL ULTIMO PEDIDO STATUS ----------------------------------");
			List<PedidoStatus> listadoUltimoPedidoStatus = servicioPedidoStatus.listarUltimosPedidoStatus(1);
			PedidoStatus pedidoStatus = listadoUltimoPedidoStatus.get(0);
			System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
			System.out.println(pedidoStatus);
			System.out.println("... MODIFICAMOS");		
			m.modifyDescriptionLastPedidoStatus(m, servicioPedidoStatus, pedidoStatus, "DESCRIPCION MODIFICADA");
			System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
			m.printLastPedidoStatus(m, servicioPedidoStatus, 1);
			System.out.println("... REVERTIMOS MODIFICACION");
			m.modifyDescriptionLastPedidoStatus(m, servicioPedidoStatus, pedidoStatus, "DESCRIPCION NO MODIFICADA");
			System.out.println(" ");
			System.out.println("// AÑADIR PEDIDO STATUS NUEVO ----------------------------------");
			System.out.println("... AGREGAMOS PEDIDO STATUS NUEVO ");
			PedidoStatus nuevoPedidoStatus = new PedidoStatus("PEDIDO STATUS NUEVO");
			servicioPedidoStatus.guardar(nuevoPedidoStatus);
			System.out.println("... LISTAMOS ULTIMOS 2 PEDIDO STATUS ");
			m.printLastPedidoStatus(m, servicioPedidoStatus, 2);
			System.out.println(" ");
			System.out.println("// ELIMINAR ULTIMO PEDIDO STATUS ---------------------------------");
			listadoUltimoPedidoStatus = servicioPedidoStatus.listarUltimosPedidoStatus(1);
			pedidoStatus = listadoUltimoPedidoStatus.get(0);
			System.out.println("... LISTAMOS ULTIMOS 2 PEDIDO STATUS ANTES DE ELIMINAR");
			m.printLastPedidoStatus(m, servicioPedidoStatus, 2);
			System.out.println("... ELIMININAMOS ULTIMO PedidoStatus");
			servicioPedidoStatus.eliminar(pedidoStatus);
			System.out.println("... LISTAMOS ULTIMOS 2 PEDIDO STATUS DESPUES DE ELIMINAR");
			m.printLastPedidoStatus(m, servicioPedidoStatus, 2);System.out.println(" ");
			
			
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("****************************************************************");
			System.out.println("TABLA PEDIDOS");
			System.out.println("****************************************************************");
			System.out.println(" ");
			System.out.println("// LISTAR ULTIMOS 3 PEDIDOS ----------------------------------");
			m.printLastPedidos(m, servicioPedido, 3);
			System.out.println(" ");
			System.out.println("// MODIFICAR EL ULTIMO PEDIDO ----------------------------------");
			List<Pedido> listadoUltimoPedido = servicioPedido.listarUltimosPedidos(1);
			Pedido pedido = listadoUltimoPedido.get(0);
			System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
			System.out.println(pedido);
			System.out.println("... MODIFICAMOS");		
			m.modifyTotalLastPedido(m, servicioPedido, pedido, Float.parseFloat("777.777"));
			System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
			m.printLastPedidos(m, servicioPedido, 1);
			System.out.println("... REVERTIMOS MODIFICACION");
			m.modifyTotalLastPedido(m, servicioPedido, pedido, Float.parseFloat("888.888"));
			System.out.println(" ");
			System.out.println("// AÑADIR PEDIDO NUEVO ----------------------------------");
			System.out.println("... AGREGAMOS PEDIDO NUEVO ");
			PedidoStatus pedidoStatusNuevo = servicioPedidoStatus.getPedidoStatus(1);
			Usuario pedidoUsuario8 = servicioUsuario.getUsuario(8);
			Pedido nuevoPedido = new Pedido(new Date(), Float.parseFloat("555.555"), pedidoStatusNuevo, pedidoUsuario8);
			servicioPedido.guardar(nuevoPedido);
			System.out.println("... LISTAMOS ULTIMOS 2 PEDIDOS ");
			m.printLastPedidos(m, servicioPedido, 2);
			System.out.println(" ");
			System.out.println("// ELIMINAR ULTIMO PEDIDOS ---------------------------------");
			listadoUltimoPedido = servicioPedido.listarUltimosPedidos(1);
			pedido = listadoUltimoPedido.get(0);
			System.out.println("... LISTAMOS ULTIMOS 2 PEDIDOS ANTES DE ELIMINAR");
			m.printLastPedidos(m, servicioPedido, 2);
			System.out.println("... ELIMININAMOS ULTIMO Pedido");
			servicioPedido.eliminar(pedido);
			System.out.println("... LISTAMOS ULTIMOS 2 PEDIDOS DESPUES DE ELIMINAR");
			m.printLastPedidos(m, servicioPedido, 2);
			
			System.out.println(" ");
			System.out.println(" ");
			System.out.println("****************************************************************");
			System.out.println("TABLA PRODUCTO");
			System.out.println("****************************************************************");
			System.out.println(" ");
			System.out.println("// LISTAR ULTIMOS 3 PRODUCTOS ----------------------------------");
			m.printLastProductos(m, servicioProducto, 3);
			System.out.println(" ");
			System.out.println("// MODIFICAR EL ULTIMO PRODUCTO ----------------------------------");
			List<Producto> listadoUltimoProducto = servicioProducto.listarUltimosProductos(1);
			Producto producto = listadoUltimoProducto.get(0);
			System.out.println("... MOSTRAMOS ANTES DE MODIFICACION");
			System.out.println(producto);
			System.out.println("... MODIFICAMOS");		
			m.modifyNombreLastProducto(m, servicioProducto, producto, "NOMBRE MODIFICADO");
			System.out.println("... MOSTRAMOS DESPUES DE MODIFICACION");
			m.printLastProductos(m, servicioProducto, 1);
			System.out.println("... REVERTIMOS MODIFICACION");
			m.modifyNombreLastProducto(m, servicioProducto, producto, "NOMBRE NO MODIFICADO");
			System.out.println(" ");
			System.out.println("// AÑADIR PRODUCTO NUEVO ----------------------------------");
			System.out.println("... AGREGAMOS PRODUCTO NUEVO ");
			Producto nuevoProducto = new Producto("PRODUCTO NUEVO", "3 kg", Float.parseFloat("555.555"), "Frutas y verduras");
			servicioProducto.guardar(nuevoProducto);
			System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTOS ");
			m.printLastProductos(m, servicioProducto, 2);
			System.out.println(" ");
			System.out.println("// ELIMINAR ULTIMO PRODUCTO ---------------------------------");
			listadoUltimoProducto = servicioProducto.listarUltimosProductos(1);
			producto = listadoUltimoProducto.get(0);
			System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTO ANTES DE ELIMINAR");
			m.printLastProductos(m, servicioProducto, 2);
			System.out.println("... ELIMININAMOS ULTIMO Producto");
			servicioProducto.eliminar(producto);
			System.out.println("... LISTAMOS ULTIMOS 2 PRODUCTO DESPUES DE ELIMINAR");
			m.printLastProductos(m, servicioProducto, 2);
			
		}
	}
	
	public void modifyNombreLastProducto(Main m, ProductoService servicioProducto, Producto producto, String newNombre) {
		servicioProducto.desalojar(producto);
		producto.setNombre(newNombre);
		servicioProducto.actualizar(producto);
	}
	
	public void printLastProductos(Main m, ProductoService servicioProducto, int many) {
		List<Producto> listadoUltimosProductos = servicioProducto.listarUltimosProductos(many);
		m.printProductos(listadoUltimosProductos);
	}
	
	public void printProductos(List<Producto> productos) {
		for(Producto producto: productos) {
			System.out.println(producto);
		}
	}
	
	public void printLastPedidos(Main m, PedidoService servicioPedido, int many) {
		List<Pedido> listadoUltimosPedidos = servicioPedido.listarUltimosPedidos(many);
		m.printPedidos(listadoUltimosPedidos);
	}
	
	public void printPedidos(List<Pedido> pedidos) {
		for(Pedido pedido: pedidos) {
			System.out.println(pedido);
		}
	}
	
	public void modifyTotalLastPedido(Main m, PedidoService servicioPedido, Pedido pedido, float newTotal) {
		servicioPedido.desalojar(pedido);
		pedido.setTotal(newTotal);
		servicioPedido.actualizar(pedido);
	}
	
	public void modifyDescriptionLastPedidoStatus(Main m, PedidoStatusService servicioPedidoStatus, PedidoStatus pedidoStatus, String newDescription) {
		servicioPedidoStatus.desalojar(pedidoStatus);
		pedidoStatus.setDescripcion(newDescription);
		servicioPedidoStatus.actualizar(pedidoStatus);
	}
	
	public void printLastPedidoStatus(Main m, PedidoStatusService servicioPedidoStatus, int many) {
		List<PedidoStatus> listadoUltimosPedidoStatus = servicioPedidoStatus.listarUltimosPedidoStatus(many);
		m.printPedidoStatus(listadoUltimosPedidoStatus);
	}
	
	public void printPedidoStatus(List<PedidoStatus> pedidoStatusList) {
		for(PedidoStatus pedidoStatus: pedidoStatusList) {
			System.out.println(pedidoStatus);
		}
	}
	
	public void printLastRoles(Main m, RolService servicioRol, int many) {
		List<Rol> listadoUltimosRoles = servicioRol.listarUltimosRoles(many);
		m.printRoles(listadoUltimosRoles);
	}
	
	public void printRoles(List<Rol> roles) {
		for(Rol rol: roles) {
			System.out.println(rol);
		}
	}
	
	public void modifyDescriptionLastRol(Main m, RolService servicioRol, Rol rol, String newDescription) {
		servicioRol.desalojar(rol);
		rol.setDescripcion(newDescription);
		servicioRol.actualizar(rol);
	}
	
	public void modifyNameLastUsuario(Main m, UsuarioService servicioUsuario, Usuario usuario, String newName) {
		servicioUsuario.desalojar(usuario);
		usuario.setNombre(newName);
		servicioUsuario.actualizar(usuario);
	}
	
	public void printLastUsuarios(Main m, UsuarioService servicioUsuario, int many) {
		List<Usuario> listadoUltimosUsuarios = servicioUsuario.listarUltimosUsuarios(many);
		m.printUsuarios(listadoUltimosUsuarios);
	}
		
	public void printUsuarios(List<Usuario> usuarios) {
		for(Usuario usuario: usuarios) {
			System.out.println(usuario);
		}
	}
	
}
