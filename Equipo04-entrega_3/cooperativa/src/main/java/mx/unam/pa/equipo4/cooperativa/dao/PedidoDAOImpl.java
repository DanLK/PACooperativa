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

@Repository
public class PedidoDAOImpl implements PedidoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Pedido> getAllPedidos() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root);
		
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidos = query.getResultList();
		
		return pedidos;
	}
	
	@Override
	public List<Pedido> getFirstPedidos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root);
		Query<Pedido> query = session.createQuery(criteria).setMaxResults(many);
		List<Pedido> pedidos = query.getResultList();

		return pedidos;
	}

	@Override
	public List<Pedido> getLastPedidos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.orderBy(builder.desc(root.get("id")));
		Query<Pedido> query = session.createQuery(criteria).setMaxResults(many);
		List<Pedido> pedidos = query.getResultList();

		return pedidos;
	}
	
	@Override
	public void evict(Pedido pedido) {
		sessionFactory.getCurrentSession().evict(pedido);
	}
	
	@Override
	public void update(Pedido pedido) {
		sessionFactory.getCurrentSession().update(pedido);
	}
	
	@Override
	public void remove(Pedido pedido) {
		sessionFactory.getCurrentSession().remove(pedido);
	}
	
	@Override
	public void save(Pedido pedido) {
		sessionFactory.getCurrentSession().save(pedido);
	}
	
	@Override
	public Pedido getPedido(int id) {
		return sessionFactory.getCurrentSession().get(Pedido.class, id);
	}
}