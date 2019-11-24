package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.ProductoPedidoDAO;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;

@Service
@Transactional
public class ProductoPedidoServiceImpl implements ProductoPedidoService {
	@Autowired
	private ProductoPedidoDAO dao;
	
	@Override
	public List<ProductoPedido> listarProductoPedidos() {
		return dao.getAllProductoPedidos();
	}

	@Override
	public List<ProductoPedido> listarPrimerosProductoPedidos(int many) {
		return dao.getFirstProductoPedidos(many);
	}
	
	@Override
	public List<ProductoPedido> listarUltimosProductoPedidos(int many) {
		return dao.getLastProductoPedidos(many);
	}
	
	@Override
	public void desalojar(ProductoPedido productoPedido) {
		dao.evict(productoPedido);
	}
	
	@Override
	public void eliminar(ProductoPedido productoPedido) {
		dao.remove(productoPedido);
	}
	
	@Override
	public void actualizar(ProductoPedido productoPedido) {
		dao.update(productoPedido);
	}
	
	@Override
	public void guardar(ProductoPedido productoPedido) {
		dao.save(productoPedido);
	}
	
	@Override
	public ProductoPedido getProductoPedido(int id) {
		return dao.getProductoPedido(id);
	}
	
}
