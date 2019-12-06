package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Producto;

// Clase para la definicion del dao en PRODUCTO
public interface ProductoDAO {
	
	// Funcion para obtener todos los productos
	public List<Producto> getAllProductos();
	
	// Funcion para obtener todos los productos de un departamento en especifico
	public List<Producto> getProductosDepto(String depto);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos en orden descendente por id
	public List<Producto> getLastProductos(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void evict(Producto producto);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void update(Producto producto);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void remove(Producto producto);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void save(Producto producto);
	
	// Funcion para traer un producto con un id en especifico
	public Producto getProducto(int id);
	
}