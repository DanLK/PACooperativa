package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import mx.unam.pa.equipo4.ManejoFechas;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

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
	public List<Pedido> getPedidosSemana(){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		
		ManejoFechas test = new ManejoFechas();
		Date fechaFinal = new Date();
		Date fechaInicial = test.restarDias(fechaFinal, 7);
		System.out.println(fechaFinal);
		System.out.println(fechaInicial);
		
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root).where(builder.between(root.get("fechaRegistro"), fechaInicial, fechaFinal));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosSemana = query.getResultList();
		
		return pedidosSemana;
		
		
	}
	
	@Override
	public List<Pedido> getPedidosUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root).where(builder.equal(root.get("Usuario"), user));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosUsuario = query.getResultList();
		return pedidosUsuario;
	}
	
	@Override
	public List<Pedido> getPedidosEnviados(){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root).where(builder.equal(root.get("pedidoStatus"),1));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosEnviados = query.getResultList();
		
		return pedidosEnviados;
	}
	
	@Override
	public List<Pedido> getPedidosPagados(){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root).where(builder.equal(root.get("pedidoStatus"),2));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosPagados = query.getResultList();
		
		return pedidosPagados;
	}
	
	@Override
	public List<Pedido> getPedidosEntregados(){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root).where(builder.equal(root.get("pedidoStatus"),3));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosEntregados = query.getResultList();
		
		return pedidosEntregados;
	}
	
	@Override
	public List<Pedido> getPedidosEnviadosUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root).where(builder.and(builder.equal(root.get("pedidoStatus"), 1),builder.equal(root.get("Usuario"), user)));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosEnviadosUsuario = query.getResultList();
		return pedidosEnviadosUsuario;
	}
	
	@Override
	public List<Pedido> getPedidosPagadosUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root).where(builder.and(builder.equal(root.get("pedidoStatus"), 2),builder.equal(root.get("Usuario"), user)));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosPagadosUsuario = query.getResultList();
		return pedidosPagadosUsuario;
	}
	
	@Override
	public List<Pedido> getPedidosEntregadosUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root).where(builder.and(builder.equal(root.get("pedidoStatus"), 3),builder.equal(root.get("Usuario"), user)));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosEntregadosUsuario = query.getResultList();
		return pedidosEntregadosUsuario;
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