package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.unam.pa.equipo4.cooperativa.model.Producto;

// Clase para la implementacion del dao en PRODUCTO
@Repository
public class ProductoDAOImpl implements ProductoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// Funcion para obtener todos los productos
	@Override
	public List<Producto> getAllProductos() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
		
		Root<Producto> root = criteria.from(Producto.class);
		criteria.select(root);
		
		Query<Producto> query = session.createQuery(criteria);
		List<Producto> productos = query.getResultList();
		
		return productos;
	}
	
	// Funcion para obtener todos los productos de un departamento en especifico
	@Override
	public List<Producto> getProductosDepto(String depto) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
		Root<Producto> root = criteria.from(Producto.class);
		
		// Aqui definimos el criterio de busqueda de departamentos
		criteria.select(root).where(builder.equal(root.get("departamento"),depto));
		
		Query<Producto> query = session.createQuery(criteria);
		List<Producto> productosDepto = query.getResultList();
		return productosDepto;
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos en orden descendente por id
	@Override
	public List<Producto> getLastProductos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
		Root<Producto> root = criteria.from(Producto.class);
		
		// Aqui definimos el criterio de ordenarlo descendientemente por id
		criteria.orderBy(builder.desc(root.get("id")));
		
		// Aqui definimos el limite maximo de resultados
		Query<Producto> query = session.createQuery(criteria).setMaxResults(many);
		
		List<Producto> productos = query.getResultList();

		return productos;
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void evict(Producto producto) {
		sessionFactory.getCurrentSession().evict(producto);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void update(Producto producto) {
		sessionFactory.getCurrentSession().update(producto);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void remove(Producto producto) {
		sessionFactory.getCurrentSession().remove(producto);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void save(Producto producto) {
		sessionFactory.getCurrentSession().save(producto);
	}
	
	// Funcion para traer un producto con un id en especifico
	@Override
	public Producto getProducto(int id) {
		return sessionFactory.getCurrentSession().get(Producto.class, id);
	}
}