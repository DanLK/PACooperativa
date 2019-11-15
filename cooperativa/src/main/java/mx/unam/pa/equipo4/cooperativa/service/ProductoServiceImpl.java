package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.ProductoDAO;
import mx.unam.pa.equipo4.cooperativa.model.Producto;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoDAO dao;
	
	@Override
	public List<Producto> listarProductos() {
		return dao.getAllProductos();
	}
	
	@Override
	public List<Producto> listarProductosDepto(String depto) {
		return dao.getProductosDepto(depto);
	}
	
	@Override
	public List<Producto> listarUltimosProductos(int many) {
		return dao.getLastProductos(many);
	}

	@Override
	public void desalojar(Producto producto) {
		dao.evict(producto);
	}
	
	@Override
	public void actualizar(Producto producto) {
		dao.update(producto);
	}
	
	@Override
	public void eliminar(Producto producto) {
		dao.remove(producto);
	}
	
	@Override
	public void guardar(Producto producto) {
		dao.save(producto);
	}
	
	@Override
	public Producto getProducto(int id) {
		return dao.getProducto(id);
	}
}
