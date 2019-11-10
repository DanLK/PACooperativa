package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Usuario;

public interface UsuarioService {
	public List<Usuario> listarUsuarios();
	public void guardar(Usuario usuario);
	public Usuario getUsuario(int id);
}