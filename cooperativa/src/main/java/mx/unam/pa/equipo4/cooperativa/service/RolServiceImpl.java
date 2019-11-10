package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.RolDAO;
import mx.unam.pa.equipo4.cooperativa.model.Rol;

@Service
@Transactional
public class RolServiceImpl implements RolService {
	@Autowired
	private RolDAO dao;
	
	@Override
	public List<Rol> listarRoles() {
		return dao.getAllRoles();
	}

	@Override
	public void guardar(Rol rol) {
		dao.save(rol);
	}
	
	@Override
	public Rol getRol(int id) {
		return dao.getRol(id);
	}
}
