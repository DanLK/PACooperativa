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

@Repository
public class ProductoPedidoDAOImpl implements ProductoPedidoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
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
	
	@Override
	public List<ProductoPedido> getFirstProductoPedidos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductoPedido> criteria = builder.createQuery(ProductoPedido.class);
		Root<ProductoPedido> root = criteria.from(ProductoPedido.class);
		criteria.select(root);
		Query<ProductoPedido> query = session.createQuery(criteria).setMaxResults(many);
		List<ProductoPedido> productoPedidos = query.getResultList();

		return productoPedidos;
	}

	@Override
	public List<ProductoPedido> getLastProductoPedidos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductoPedido> criteria = builder.createQuery(ProductoPedido.class);
		Root<ProductoPedido> root = criteria.from(ProductoPedido.class);
		criteria.orderBy(builder.desc(root.get("id")));
		Query<ProductoPedido> query = session.createQuery(criteria).setMaxResults(many);
		List<ProductoPedido> productoPedidos = query.getResultList();

		return productoPedidos;
	}
	
	@Override
	public void evict(ProductoPedido productoPedido) {
		sessionFactory.getCurrentSession().evict(productoPedido);
	}
	
	@Override
	public void update(ProductoPedido productoPedido) {
		sessionFactory.getCurrentSession().update(productoPedido);
	}
	
	@Override
	public void remove(ProductoPedido productoPedido) {
		sessionFactory.getCurrentSession().remove(productoPedido);
	}
	
	@Override
	public void save(ProductoPedido productoPedido) {
		sessionFactory.getCurrentSession().save(productoPedido);
	}
	
	@Override
	public ProductoPedido getProductoPedido(int id) {
		return sessionFactory.getCurrentSession().get(ProductoPedido.class, id);
	}
	
	@Override
	public List<ProductoPedido> getProductoPedidosEnPedido(Pedido pedido) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProductoPedido> criteria = builder.createQuery(ProductoPedido.class);
		Root<ProductoPedido> root = criteria.from(ProductoPedido.class);
		criteria.select(root).where(builder.equal(root.get("pedido"), pedido));
		Query<ProductoPedido> query = session.createQuery(criteria);
		List<ProductoPedido> productoPedidosEnPedido = query.getResultList();
		return productoPedidosEnPedido;
	}
	
}