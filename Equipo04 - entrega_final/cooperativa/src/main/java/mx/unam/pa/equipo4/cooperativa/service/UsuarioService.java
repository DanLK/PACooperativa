package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.formas.LoginFrm;

// Clase para la definicion del Service en USUARIO
public interface UsuarioService {
	
	// Funcion para obtener todos los usuarios
	public List<Usuario> listarUsuarios();
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de usuarios en orden ascendente por id
	public List<Usuario> listarPrimerosUsuarios(int many);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de usuarios en orden descendente por id
	public List<Usuario> listarUltimosUsuarios(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void desalojar(Usuario usuario);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void actualizar(Usuario usuario);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void eliminar(Usuario usuario);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void guardar(Usuario usuario);
	
	// Funcion para traer un usuario con un id en especifico
	public Usuario getUsuario(int id);
	
	// Funcion para validar el usuario y contraseña para logearse
	public Usuario validarUsuario(LoginFrm login);
	
}