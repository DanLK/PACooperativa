package mx.unam.pa.equipo4.cooperativa.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

// Clase para el manejo de operaciones con fechas
public class ManejoFechas {
	
	// Funcion para obtener una fecha restandole una cantidad de dias a la fecha de entrada.
	public Date restarDias(Date fecha, int dias) {
		LocalDateTime ldt = 
				LocalDateTime.ofInstant(fecha.toInstant(),
						ZoneId.systemDefault()).minusDays(dias);
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	// Funcion para crear una fecha de una cadena
	public Date createDate(String strDate) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
	}
	
}
