package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.model.Login;

public interface UsuarioDAO {
	
	public List<Usuario> getAllUsuarios();
	
	public void save(Usuario usuario);
	
	public Usuario getUsuario(int id);
	
	public Usuario validarUsuario(Login login);
	
}