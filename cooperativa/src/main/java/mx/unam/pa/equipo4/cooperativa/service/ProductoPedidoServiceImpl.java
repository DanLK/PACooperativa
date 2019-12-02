package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.ProductoPedidoDAO;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;

// Clase para la implementacion del Service en PRODUCTO_PEDIDO
@Service
@Transactional
public class ProductoPedidoServiceImpl implements ProductoPedidoService {
	@Autowired
	private ProductoPedidoDAO dao;
	
	// Funcion para obtener todos los productos pedido
	@Override
	public List<ProductoPedido> listarProductoPedidos() {
		return dao.getAllProductoPedidos();
	}

	// Funcion para obtener una cantidad, definida por la entrada "many", de productos pedido en orden ascendente por id
	@Override
	public List<ProductoPedido> listarPrimerosProductoPedidos(int many) {
		return dao.getFirstProductoPedidos(many);
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos pedido en orden descendente por id
	@Override
	public List<ProductoPedido> listarUltimosProductoPedidos(int many) {
		return dao.getLastProductoPedidos(many);
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void desalojar(ProductoPedido productoPedido) {
		dao.evict(productoPedido);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void actualizar(ProductoPedido productoPedido) {
		dao.update(productoPedido);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void eliminar(ProductoPedido productoPedido) {
		dao.remove(productoPedido);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void guardar(ProductoPedido productoPedido) {
		dao.save(productoPedido);
	}
	
	// Funcion para traer un producto pedido con un id en especifico
	@Override
	public ProductoPedido getProductoPedido(int id) {
		return dao.getProductoPedido(id);
	}
	
	// Funcion para traer los productos pedido de un pedido en especifico
	@Override
	public List<ProductoPedido> listarProductoPedidosDePedido(Pedido pedido) {
		return dao.getProductoPedidosEnPedido(pedido);
	}
	
}
