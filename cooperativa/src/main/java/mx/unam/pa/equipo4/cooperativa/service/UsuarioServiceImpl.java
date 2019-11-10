package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.UsuarioDAO;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

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
	public void guardar(Usuario usuario) {
		dao.save(usuario);
	}
	
	@Override
	public Usuario getUsuario(int id) {
		return dao.getUsuario(id);
	}
}
