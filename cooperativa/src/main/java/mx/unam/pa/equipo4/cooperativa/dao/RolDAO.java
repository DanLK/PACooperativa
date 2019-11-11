package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Rol;

public interface RolDAO {
	
	public List<Rol> getAllRoles();
	
	public List<Rol> getLastRoles(int many);
	
	public void evict(Rol rol);
	
	public void update(Rol rol);
	
	public void remove(Rol rol);
	
	public void save(Rol rol);
	
	public Rol getRol(int id);
	
}