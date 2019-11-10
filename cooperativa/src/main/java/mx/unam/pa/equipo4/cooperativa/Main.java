package mx.unam.pa.equipo4.cooperativa;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mx.unam.pa.equipo4.cooperativa.config.HibernateConfig;
import mx.unam.pa.equipo4.cooperativa.model.Rol;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
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
			
			
			System.out.println("****************************************************************");
			System.out.println("TABLA USUARIO");
			System.out.println("****************************************************************");
			System.out.println("// LISTAR ULTIMOS 10 USUARIOS ----------------------------------");
			m.printLastUsuarios(m, servicioUsuario, 10);
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
			System.out.println("// AÑADIR USUARIO NUEVO ----------------------------------");
			System.out.println("... AGREGAMOS USUARIO USUARIO ");
			Rol rolSocio = servicioRol.getRol(2);
			Usuario nuevoUsuario = new Usuario("nuevo usuario", "agregado", "newUser", "newPass", "new@correo.com", null, rolSocio);
			servicioUsuario.guardar(nuevoUsuario);
			System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS ");
			m.printLastUsuarios(m, servicioUsuario, 2);
			System.out.println("// ELIMINAR ULTIMO USUARIO ---------------------------------");
			listadoUltimoUsuario = servicioUsuario.listarUltimosUsuarios(1);
			usuario = listadoUltimoUsuario.get(0);
			System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS ANTES DE ELIMINAR");
			m.printLastUsuarios(m, servicioUsuario, 2);
			System.out.println("... ELIMININAMOS ULTIMO USUARIO");
			servicioUsuario.eliminar(usuario);
			System.out.println("... LISTAMOS ULTIMOS 2 USUARIOS DESPUES DE ELIMINAR");
			m.printLastUsuarios(m, servicioUsuario, 2);
		}
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
