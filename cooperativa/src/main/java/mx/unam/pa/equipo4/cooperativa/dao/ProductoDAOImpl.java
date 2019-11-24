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

@Repository
public class ProductoDAOImpl implements ProductoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
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
	
	@Override
	public List<Producto> getLastProductos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
		Root<Producto> root = criteria.from(Producto.class);
		criteria.orderBy(builder.desc(root.get("id")));
		Query<Producto> query = session.createQuery(criteria).setMaxResults(many);
		List<Producto> productos = query.getResultList();

		return productos;
	}
	
	@Override
	public List<Producto> getProductosDepto(String depto) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
		Root<Producto> root = criteria.from(Producto.class);
		criteria.select(root).where(builder.equal(root.get("departamento"),depto));
		Query<Producto> query = session.createQuery(criteria);
		List<Producto> productosDepto = query.getResultList();
		return productosDepto;
	}
	
	@Override
	public void evict(Producto producto) {
		sessionFactory.getCurrentSession().evict(producto);
	}
	
	@Override
	public void update(Producto producto) {
		sessionFactory.getCurrentSession().update(producto);
	}
	
	@Override
	public void remove(Producto producto) {
		sessionFactory.getCurrentSession().remove(producto);
	}
	
	@Override
	public void save(Producto producto) {
		sessionFactory.getCurrentSession().save(producto);
	}
	
	@Override
	public Producto getProducto(int id) {
		return sessionFactory.getCurrentSession().get(Producto.class, id);
	}
}