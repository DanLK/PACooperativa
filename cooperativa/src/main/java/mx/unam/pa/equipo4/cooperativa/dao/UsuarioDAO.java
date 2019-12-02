package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.formas.LoginFrm;

// Clase para la definicion del dao en USUARIO
public interface UsuarioDAO {
	
	// Funcion para obtener todos los usuarios
	public List<Usuario> getAllUsuarios();
	
	// Funcion para obtener todos los usuarios con rol de socio
	public List<Usuario> getAllSocios();
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de usuarios en orden ascendente por id
	public List<Usuario> getFirstUsuarios(int many);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de usuarios en orden descendente por id
	public List<Usuario> getLastUsuarios(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void evict(Usuario usuario);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void update(Usuario usuario);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void remove(Usuario usuario);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void save(Usuario usuario);
	
	// Funcion para traer un usuario con un id en especifico
	public Usuario getUsuario(int id);
	
	// Funcion para validar el usuario y contraseña para logearse
	public Usuario validarUsuario(LoginFrm login);
	
}