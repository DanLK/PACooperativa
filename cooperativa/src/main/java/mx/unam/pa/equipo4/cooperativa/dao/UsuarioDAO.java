package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

public interface UsuarioDAO {
	
	public List<Usuario> getAllUsuarios();
	
	public void save(Usuario usuario);
	
	public Usuario getUsuario(int id);
	
}