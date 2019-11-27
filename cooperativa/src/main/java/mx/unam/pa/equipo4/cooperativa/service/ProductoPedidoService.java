package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;

public interface ProductoPedidoService {
	public List<ProductoPedido> listarProductoPedidos();
	public List<ProductoPedido> listarPrimerosProductoPedidos(int many);
	public List<ProductoPedido> listarUltimosProductoPedidos(int many);
	public void desalojar(ProductoPedido productoPedido);
	public void actualizar(ProductoPedido productoPedido);
	public void eliminar(ProductoPedido productoPedido);
	public void guardar(ProductoPedido productoPedido);
	public ProductoPedido getProductoPedido(int id);
	public List<ProductoPedido> listarProductoPedidosDePedido(Pedido pedido);
}