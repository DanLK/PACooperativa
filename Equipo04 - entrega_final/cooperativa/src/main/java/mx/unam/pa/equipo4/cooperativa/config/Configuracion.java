package mx.unam.pa.equipo4.cooperativa.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import mx.unam.pa.equipo4.cooperativa.config.HibernateConfig;
import mx.unam.pa.equipo4.cooperativa.config.WebMvcConfig;

/**
 * Configuración de la aplicación Web.
 * 
 * Paso 1: Crear y registrar el contenedor de Spring (Dispatcher Servlet) 
 * para procesar todas las solicitudes.
 * 
 * Esta clase también sustituye la configuración del contenedor 
 * a través del archivo web.xml
 *
 * Clase: Programación Avanzada 2020-I
 * @author Gabriel González G.
 * @date Sep 7, 2019, 1:09:54 PM
 *
 */
public class Configuracion extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
				HibernateConfig.class // Clase que declara los beans para manejo de la base de datos
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
			WebMvcConfig.class // 
		};
	}

	/**
	 * Definición de mapeos de URL que Spring evaluará. 
	 * 
	 * Por ejemplo, si se desea que todo lo que esté en la URL 
	 * http://localhost:8080/mi-app-web/spring/
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { 
				"/",
				"/spring/*", // Spring procesará todo lo que este dentro de /spring/  
			};
	}

}
