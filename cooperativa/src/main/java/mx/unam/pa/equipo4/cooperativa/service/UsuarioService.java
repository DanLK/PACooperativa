package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Usuario;

public interface UsuarioService {
	public List<Usuario> listarUsuarios();
	public List<Usuario> listarSocios();
	public List<Usuario> listarPrimerosUsuarios(int many);
	public List<Usuario> listarUltimosUsuarios(int many);
	public void desalojar(Usuario usuario);
	public void actualizar(Usuario usuario);
	public void eliminar(Usuario usuario);
	public void guardar(Usuario usuario);
	public Usuario getUsuario(int id);
}