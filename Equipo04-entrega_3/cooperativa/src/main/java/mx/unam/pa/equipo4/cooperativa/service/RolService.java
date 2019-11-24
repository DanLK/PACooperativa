package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Rol;

public interface RolService {
	public List<Rol> listarRoles();
	public List<Rol> listarUltimosRoles(int many);
	public void desalojar(Rol rol);
	public void actualizar(Rol rol);
	public void eliminar(Rol rol);
	public void guardar(Rol rol);
	public Rol getRol(int id);
}