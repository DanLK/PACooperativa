package mx.unam.pa.equipo4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class ManejoFechas {
	
	public Date restarDias(Date fecha, int dias) {
		LocalDateTime ldt = 
				LocalDateTime.ofInstant(fecha.toInstant(),
						ZoneId.systemDefault()).minusDays(dias);
		return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public Date createDate(String strDate) throws ParseException {
		return new SimpleDateFormat("yyy-MM-dd").parse(strDate);
	}
	

}
