package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.ProductoDAO;
import mx.unam.pa.equipo4.cooperativa.model.Producto;

// Clase para la implementacion del Service en PRODUCTO
@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	private ProductoDAO dao;
	
	// Funcion para obtener todos los productos
	@Override
	public List<Producto> listarProductos() {
		return dao.getAllProductos();
	}
	
	// Funcion para obtener todos los productos de un departamento en especifico
	@Override
	public List<Producto> listarProductosDepto(String depto) {
		return dao.getProductosDepto(depto);
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos en orden descendente por id
	@Override
	public List<Producto> listarUltimosProductos(int many) {
		return dao.getLastProductos(many);
	}

	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void desalojar(Producto producto) {
		dao.evict(producto);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void actualizar(Producto producto) {
		dao.update(producto);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void eliminar(Producto producto) {
		dao.remove(producto);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void guardar(Producto producto) {
		dao.save(producto);
	}
	
	// Funcion para traer un producto con un id en especifico
	@Override
	public Producto getProducto(int id) {
		return dao.getProducto(id);
	}
}
