package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;

// Clase para la definicion del dao en PRODUCTO_PEDIDO
public interface ProductoPedidoDAO {
	
	// Funcion para obtener todos los productos pedido
	public List<ProductoPedido> getAllProductoPedidos();
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos pedido en orden ascendente por id
	public List<ProductoPedido> getFirstProductoPedidos(int many);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos pedido en orden descendente por id
	public List<ProductoPedido> getLastProductoPedidos(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void evict(ProductoPedido productoPedido);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void update(ProductoPedido productoPedido);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void remove(ProductoPedido productoPedido);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void save(ProductoPedido productoPedido);
	
	// Funcion para traer un producto pedido con un id en especifico
	public ProductoPedido getProductoPedido(int id);
	
	// Funcion para traer los productos pedido de un pedido en especifico
	public List<ProductoPedido> getProductoPedidosEnPedido(Pedido pedido);
	
}