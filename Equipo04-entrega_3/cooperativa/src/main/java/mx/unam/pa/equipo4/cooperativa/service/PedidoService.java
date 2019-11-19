package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;

public interface PedidoService {
	public List<Pedido> listarPedidos();
	public List<Pedido> listarPrimerosPedidos(int many);
	public List<Pedido> listarUltimosPedidos(int many);
	public void desalojar(Pedido pedido);
	public void actualizar(Pedido pedido);
	public void eliminar(Pedido pedido);
	public void guardar(Pedido pedido);
	public Pedido getPedido(int id);
}