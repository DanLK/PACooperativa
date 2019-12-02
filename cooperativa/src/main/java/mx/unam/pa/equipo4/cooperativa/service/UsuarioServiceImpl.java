package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.UsuarioDAO;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.formas.LoginFrm;

// Clase para la implementacion del Service en USUARIO
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioDAO dao;
	
	// Funcion para obtener todos los usuarios
	@Override
	public List<Usuario> listarUsuarios() {
		return dao.getAllUsuarios();
	}

	// Funcion para obtener una cantidad, definida por la entrada "many", de usuarios en orden ascendente por id
	@Override
	public List<Usuario> listarPrimerosUsuarios(int many) {
		return dao.getFirstUsuarios(many);
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de usuarios en orden descendente por id
	@Override
	public List<Usuario> listarUltimosUsuarios(int many) {
		return dao.getLastUsuarios(many);
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void desalojar(Usuario usuario) {
		dao.evict(usuario);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void actualizar(Usuario usuario) {
		dao.update(usuario);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void eliminar(Usuario usuario) {
		dao.remove(usuario);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void guardar(Usuario usuario) {
		dao.save(usuario);
	}
	
	// Funcion para traer un usuario con un id en especifico
	@Override
	public Usuario getUsuario(int id) {
		return dao.getUsuario(id);
	}
	
	// Funcion para validar el usuario y contraseña para logearse
	@Override
	public Usuario validarUsuario(LoginFrm login) {
		return dao.validarUsuario(login);
	}
	
}
