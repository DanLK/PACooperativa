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

import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;

@Repository
public class PedidoStatusDAOImpl implements PedidoStatusDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PedidoStatus> getAllPedidoStatus() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<PedidoStatus> criteria = builder.createQuery(PedidoStatus.class);
		
		Root<PedidoStatus> root = criteria.from(PedidoStatus.class);
		criteria.select(root);
		
		Query<PedidoStatus> query = session.createQuery(criteria);
		List<PedidoStatus> pedidoStatus = query.getResultList();
		
		return pedidoStatus;
	}
	
	@Override
	public List<PedidoStatus> getLastPedidoStatus(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PedidoStatus> criteria = builder.createQuery(PedidoStatus.class);
		Root<PedidoStatus> root = criteria.from(PedidoStatus.class);
		criteria.orderBy(builder.desc(root.get("id")));
		Query<PedidoStatus> query = session.createQuery(criteria).setMaxResults(many);
		List<PedidoStatus> pedidoStatus = query.getResultList();

		return pedidoStatus;
	}
	
	@Override
	public void evict(PedidoStatus pedidoStatus) {
		sessionFactory.getCurrentSession().evict(pedidoStatus);
	}
	
	@Override
	public void update(PedidoStatus pedidoStatus) {
		sessionFactory.getCurrentSession().update(pedidoStatus);
	}
	
	@Override
	public void remove(PedidoStatus pedidoStatus) {
		sessionFactory.getCurrentSession().remove(pedidoStatus);
	}
	
	@Override
	public void save(PedidoStatus pedidoStatus) {
		sessionFactory.getCurrentSession().save(pedidoStatus);
	}
	
	@Override
	public PedidoStatus getPedidoStatus(int id) {
		return sessionFactory.getCurrentSession().get(PedidoStatus.class, id);
	}
}