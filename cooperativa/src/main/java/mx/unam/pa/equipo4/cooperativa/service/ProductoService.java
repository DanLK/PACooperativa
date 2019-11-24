package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Producto;

public interface ProductoService {
	public List<Producto> listarProductos();
	public List<Producto> listarProductosDepto(String depto);
	public List<Producto> listarUltimosProductos(int many);
	public void desalojar(Producto producto);
	public void actualizar(Producto producto);
	public void eliminar(Producto producto);
	public void guardar(Producto producto);
	public Producto getProducto(int id);
}