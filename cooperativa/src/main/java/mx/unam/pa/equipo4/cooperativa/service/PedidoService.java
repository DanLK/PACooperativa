package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

public interface PedidoService {
	public List<Pedido> listarPedidos();
	public List<Pedido> listarPedidosSemana();
	public List<Pedido> listarPedidosUsuario(Usuario user);
	public List<Pedido> listarPedidosEnviados();
	public List<Pedido> listarPedidosPagados();
	public List<Pedido> listarPedidosEntregados();
	public List<Pedido> listarPedidosEnviadosUsuarios(Usuario user);
	public List<Pedido> listarPedidosPagadosUsuarios(Usuario user);
	public List<Pedido> listarPedidosEntregadosUsuarios(Usuario user);
	public List<Pedido> listarPrimerosPedidos(int many);
	public List<Pedido> listarUltimosPedidos(int many);
	public void desalojar(Pedido pedido);
	public void actualizar(Pedido pedido);
	public void eliminar(Pedido pedido);
	public void guardar(Pedido pedido);
	public Pedido getPedido(int id);
	
}