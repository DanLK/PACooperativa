package mx.unam.pa.equipo4.cooperativa;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mx.unam.pa.equipo4.cooperativa.config.HibernateConfig;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.service.UsuarioService;

public class Main {
	public static void main(String[] args) {
		Main m = new Main();
		
		try(AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(HibernateConfig.class) ){

			UsuarioService servicio = context.getBean(UsuarioService.class);
			List<Usuario> listadoUsuarios = servicio.listarUsuarios();
			
			m.printUsuarios(listadoUsuarios);
			
			
			// System.out.println("Creando nuevo usuario");
			// Autor autor = new Autor("Richard", "Mattenson");
			// servicio.guardar(autor);
			
			// listadoAutores = servicio.listarAutores();
			// m.printAutores(listadoAutores);
		}
	}
	
	public void printUsuarios(List<Usuario> usuarios) {
		for(Usuario usuario: usuarios) {
			System.out.println(usuario);
		}
	}
}
