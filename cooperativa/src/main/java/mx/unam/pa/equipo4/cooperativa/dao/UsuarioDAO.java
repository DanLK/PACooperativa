package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

public interface UsuarioDAO {
	
	public List<Usuario> getAllUsuarios();
	
	public List<Usuario> getFirstUsuarios(int many);
	
	public List<Usuario> getLastUsuarios(int many);
	
	public void evict(Usuario usuario);
	
	public void update(Usuario usuario);
	
	public void delete(Usuario usuario);
	
	public void save(Usuario usuario);
	
	public Usuario getUsuario(int id);
	
}