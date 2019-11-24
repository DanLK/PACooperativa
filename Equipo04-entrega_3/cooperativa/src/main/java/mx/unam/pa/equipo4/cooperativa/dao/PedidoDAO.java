package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;

public interface PedidoDAO {
	
	public List<Pedido> getAllPedidos();
	
	public List<Pedido> getFirstPedidos(int many);
	
	public List<Pedido> getLastPedidos(int many);
	
	public void evict(Pedido pedido);
	
	public void update(Pedido pedido);
	
	public void remove(Pedido pedido);
	
	public void save(Pedido pedido);
	
	public Pedido getPedido(int id);
	
}