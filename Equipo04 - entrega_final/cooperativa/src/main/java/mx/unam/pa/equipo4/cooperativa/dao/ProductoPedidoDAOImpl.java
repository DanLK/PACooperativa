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

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.ProductoPedido;

// Clase para la implementacion del dao en PRODUCTO_PEDIDO
@Repository
public class ProductoPedidoDAOImpl implements ProductoPedidoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// Funcion para obtener todos los productos pedido
	@Override
	public List<ProductoPedido> getAllProductoPedidos() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<ProductoPedido> criteria = builder.createQuery(ProductoPedido.class);
		
		Root<ProductoPedido> root = criteria.from(ProductoPedido.class);
		criteria.select(root);
		
		Query<ProductoPedido> query = session.createQuery(criteria);
		List<ProductoPedido> productoPedidos = query.getResultList();
		
		return productoPedidos;
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de productos pedido en orden ascendente por id
	@Override
	public List<ProductoPedido> getFirstProductoPedidos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductoPedido> criteria = builder.createQuery(ProductoPedido.class);
		Root<ProductoPedido> root = criteria.from(ProductoPedido.class);
		criteria.select(root);
		
		// Aqui definimos la cantidad de resultados
		Query<ProductoPedido> query = session.createQuery(criteria).setMaxResults(many);
		
		List<ProductoPedido> productoPedidos = query.getResultList();

		return productoPedidos;
	}

	// Funcion para obtener una cantidad, definida por la entrada "many", de productos pedido en orden descendente por id
	@Override
	public List<ProductoPedido> getLastProductoPedidos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductoPedido> criteria = builder.createQuery(ProductoPedido.class);
		Root<ProductoPedido> root = criteria.from(ProductoPedido.class);
		
		// Aqui definimos el criterio de ordenarlo descendientemente por id
		criteria.orderBy(builder.desc(root.get("id")));
		
		// Aqui definimos el limite maximo de resultados
		Query<ProductoPedido> query = session.createQuery(criteria).setMaxResults(many);
		
		List<ProductoPedido> productoPedidos = query.getResultList();

		return productoPedidos;
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void evict(ProductoPedido productoPedido) {
		sessionFactory.getCurrentSession().evict(productoPedido);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void update(ProductoPedido productoPedido) {
		sessionFactory.getCurrentSession().update(productoPedido);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void remove(ProductoPedido productoPedido) {
		sessionFactory.getCurrentSession().remove(productoPedido);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void save(ProductoPedido productoPedido) {
		sessionFactory.getCurrentSession().save(productoPedido);
	}
	
	// Funcion para traer un producto pedido con un id en especifico
	@Override
	public ProductoPedido getProductoPedido(int id) {
		return sessionFactory.getCurrentSession().get(ProductoPedido.class, id);
	}
	
	// Funcion para traer los productos pedido de un pedido en especifico
	@Override
	public List<ProductoPedido> getProductoPedidosEnPedido(Pedido pedido) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductoPedido> criteria = builder.createQuery(ProductoPedido.class);
		Root<ProductoPedido> root = criteria.from(ProductoPedido.class);
		
		// Aqui definimos el criterio del pedido al que pertenecen los productos pedido
		criteria.select(root).where(builder.equal(root.get("pedido"), pedido));
		
		Query<ProductoPedido> query = session.createQuery(criteria);
		List<ProductoPedido> productoPedidosEnPedido = query.getResultList();
		return productoPedidosEnPedido;
	}
	
}