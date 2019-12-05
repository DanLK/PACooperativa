package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Rol;

// Clase para la definicion del dao en ROL
public interface RolDAO {
	
	// Funcion para obtener todos los roles
	public List<Rol> getAllRoles();
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de roles en orden descendente por id
	public List<Rol> getLastRoles(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void evict(Rol rol);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void update(Rol rol);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void remove(Rol rol);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void save(Rol rol);
	
	// Funcion para traer un rol con un id en especifico
	public Rol getRol(int id);
	
}