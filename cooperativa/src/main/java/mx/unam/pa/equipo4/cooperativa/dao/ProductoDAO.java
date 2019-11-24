package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Producto;

public interface ProductoDAO {
	
	public List<Producto> getAllProductos();
	
	public List<Producto> getProductosDepto(String depto);
	
	public List<Producto> getLastProductos(int many);
	
	public void evict(Producto producto);
	
	public void update(Producto producto);
	
	public void remove(Producto producto);
	
	public void save(Producto producto);
	
	public Producto getProducto(int id);
	
}