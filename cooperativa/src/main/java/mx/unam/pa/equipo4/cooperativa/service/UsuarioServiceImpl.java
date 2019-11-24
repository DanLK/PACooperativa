package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.UsuarioDAO;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.formas.LoginFrm;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioDAO dao;
	
	@Override
	public List<Usuario> listarUsuarios() {
		return dao.getAllUsuarios();
	}

	@Override
	public List<Usuario> listarPrimerosUsuarios(int many) {
		return dao.getFirstUsuarios(many);
	}
	
	@Override
	public List<Usuario> listarUltimosUsuarios(int many) {
		return dao.getLastUsuarios(many);
	}
	
	@Override
	public void desalojar(Usuario usuario) {
		dao.evict(usuario);
	}
	
	@Override
	public void eliminar(Usuario usuario) {
		dao.remove(usuario);
	}
	
	@Override
	public void actualizar(Usuario usuario) {
		dao.update(usuario);
	}
	
	@Override
	public void guardar(Usuario usuario) {
		dao.save(usuario);
	}
	
	@Override
	public Usuario getUsuario(int id) {
		return dao.getUsuario(id);
	}
	
	@Override
	public Usuario validarUsuario(LoginFrm login) {
		return dao.validarUsuario(login);
	}
}
