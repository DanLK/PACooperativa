package mx.unam.pa.equipo4.cooperativa;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  

@Controller  
public class HelloController {  
@RequestMapping("/")  
    public String display()  
    {  
        return "index";  
    }     
}  