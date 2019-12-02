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

// Clase para la implementacion del dao en PEDIDO_STATUS
@Repository
public class PedidoStatusDAOImpl implements PedidoStatusDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// Funcion para obtener todos los status
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
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de status en orden descendente por id
	@Override
	public List<PedidoStatus> getLastPedidoStatus(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PedidoStatus> criteria = builder.createQuery(PedidoStatus.class);
		Root<PedidoStatus> root = criteria.from(PedidoStatus.class);
		
		// Aqui definimos el criterio de ordenarlo descendientemente por id
		criteria.orderBy(builder.desc(root.get("id")));
		
		// Aqui definimos el limite maximo de resultados
		Query<PedidoStatus> query = session.createQuery(criteria).setMaxResults(many);
		
		List<PedidoStatus> pedidoStatus = query.getResultList();

		return pedidoStatus;
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void evict(PedidoStatus pedidoStatus) {
		sessionFactory.getCurrentSession().evict(pedidoStatus);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void update(PedidoStatus pedidoStatus) {
		sessionFactory.getCurrentSession().update(pedidoStatus);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void remove(PedidoStatus pedidoStatus) {
		sessionFactory.getCurrentSession().remove(pedidoStatus);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void save(PedidoStatus pedidoStatus) {
		sessionFactory.getCurrentSession().save(pedidoStatus);
	}
	
	// Funcion para traer un status con un id en especifico
	@Override
	public PedidoStatus getPedidoStatus(int id) {
		return sessionFactory.getCurrentSession().get(PedidoStatus.class, id);
	}
	
}