package mx.unam.pa.equipo4.cooperativa;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.model.Rol;
import mx.unam.pa.equipo4.cooperativa.model.Login;
import mx.unam.pa.equipo4.cooperativa.service.UsuarioService;
import mx.unam.pa.equipo4.cooperativa.service.RolService;

@Controller
public class LoginController {
  @Autowired
  UsuarioService usuarioService;
  RolService rolService;
  @RequestMapping(value = "/cooperativa", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("index");
    mav.addObject("login", new Login());
    return mav;
  }
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("login") Login login) {
    ModelAndView mav = null;
    Usuario user = usuarioService.validarUsuario(login);
    
    if (null != user) {
    	//Rol rol = rolService.getRol(user.getId());
        //System.out.println(rol);
	    mav = new ModelAndView("welcome");
	    mav.addObject("firstname", user.getNombre());
    } else {
	    mav = new ModelAndView("index");
	    mav.addObject("message", "Username or Password is wrong!!");
    }
    return mav;
  }
}