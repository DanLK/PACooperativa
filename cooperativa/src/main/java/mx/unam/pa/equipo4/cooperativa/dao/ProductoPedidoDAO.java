package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;

public interface ProductoPedidoDAO {
	
	public List<ProductoPedido> getAllProductoPedidos();
	
	public List<ProductoPedido> getFirstProductoPedidos(int many);
	
	public List<ProductoPedido> getLastProductoPedidos(int many);
	
	public void evict(ProductoPedido productoPedido);
	
	public void update(ProductoPedido productoPedido);
	
	public void remove(ProductoPedido productoPedido);
	
	public void save(ProductoPedido productoPedido);
	
	public ProductoPedido getProductoPedido(int id);
	
	public List<ProductoPedido> getProductoPedidosEnPedido(Pedido pedido);
	
}