package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.RolDAO;
import mx.unam.pa.equipo4.cooperativa.model.Rol;

//Clase para la definicion del Service en ROL
@Service
@Transactional
public class RolServiceImpl implements RolService {
	@Autowired
	private RolDAO dao;
	
	// Funcion para obtener todos los roles
	@Override
	public List<Rol> listarRoles() {
		return dao.getAllRoles();
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de roles en orden descendente por id
	@Override
	public List<Rol> listarUltimosRoles(int many) {
		return dao.getLastRoles(many);
	}

	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void desalojar(Rol rol) {
		dao.evict(rol);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void actualizar(Rol rol) {
		dao.update(rol);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void eliminar(Rol rol) {
		dao.remove(rol);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void guardar(Rol rol) {
		dao.save(rol);
	}
	
	// Funcion para traer un rol con un id en especifico
	@Override
	public Rol getRol(int id) {
		return dao.getRol(id);
	}
	
}