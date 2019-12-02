package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Rol;

// Clase para la definicion del Service en ROL
public interface RolService {
	
	// Funcion para obtener todos los roles
	public List<Rol> listarRoles();
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de roles en orden descendente por id
	public List<Rol> listarUltimosRoles(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void desalojar(Rol rol);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void actualizar(Rol rol);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void eliminar(Rol rol);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void guardar(Rol rol);
	
	// Funcion para traer un rol con un id en especifico
	public Rol getRol(int id);
	
}