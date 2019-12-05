package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;

// Clase para la definicion del Service en PRODUCTO_PEDIDO
public interface ProductoPedidoService {
	
	// Funcion para obtener todos los productos pedido
	public List<ProductoPedido> listarProductoPedidos();
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos pedido en orden ascendente por id
	public List<ProductoPedido> listarPrimerosProductoPedidos(int many);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos pedido en orden descendente por id
	public List<ProductoPedido> listarUltimosProductoPedidos(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void desalojar(ProductoPedido productoPedido);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void actualizar(ProductoPedido productoPedido);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void eliminar(ProductoPedido productoPedido);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void guardar(ProductoPedido productoPedido);
	
	// Funcion para traer un producto pedido con un id en especifico
	public ProductoPedido getProductoPedido(int id);
	
	// Funcion para traer los productos pedido de un pedido en especifico
	public List<ProductoPedido> listarProductoPedidosDePedido(Pedido pedido);
	
}