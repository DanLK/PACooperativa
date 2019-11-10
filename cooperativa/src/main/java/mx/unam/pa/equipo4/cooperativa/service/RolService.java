package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Rol;

public interface RolService {
	public List<Rol> listarRoles();
	public void guardar(Rol rol);
	public Rol getRol(int id);
}