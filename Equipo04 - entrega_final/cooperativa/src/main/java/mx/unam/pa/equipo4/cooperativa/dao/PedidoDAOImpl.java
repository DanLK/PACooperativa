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

import mx.unam.pa.equipo4.cooperativa.utils.ManejoFechas;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

// Clase para la implementacion del dao en PEDIDO
@Repository
public class PedidoDAOImpl implements PedidoDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// Funcion para obtener TODOS los pedidos de la base de datos
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
	
	// Funcion para obtener los pedidos entre el rango entre la fecha actual y hace una semana
	@Override
	public List<Pedido> getPedidosSemana(){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		
		// Instanciamos un objeto de la clase de manejo de fechas
		ManejoFechas test = new ManejoFechas();
		// Obtenemos la fecha actual que sera el limite superior en nuestro rango
		Date fechaFinal = new Date();
		// Obtenemos la fecha hace 7 dias que sera el limite inferior en nuestro rango
		Date fechaInicial = test.restarDias(fechaFinal, 7);
		
		Root<Pedido> root = criteria.from(Pedido.class);
		// Armamos el criterio de busqueda para consultar la base de datos
		criteria.select(root).where(builder.between(root.get("fechaRegistro"), fechaInicial, fechaFinal));
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosSemana = query.getResultList();
		
		return pedidosSemana;
		
		
	}
	
	// Funcion para obtener todos los pedidos asignados a un usuario en especifico
	@Override
	public List<Pedido> getPedidosUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		// Aqui definimos el criterio de busqueda para definir que buscamos los pedidos del usuario en la entrada
		criteria.select(root).where(builder.equal(root.get("usuario"), user));
		
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosUsuario = query.getResultList();
		return pedidosUsuario;
	}
	
	// Funcion para obtener todos los pedidos con Status de "Enviado"
	@Override
	public List<Pedido> getPedidosEnviados(){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		// Aqui definimos el criterio, buscando los pedidos con status 1, es decir "Enviado"
		criteria.select(root).where(builder.equal(root.get("pedidoStatus"),1));
		
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosEnviados = query.getResultList();
		
		return pedidosEnviados;
	}
	
	// Funcion para obtener todos los pedidos con Status "Pagado"
	@Override
	public List<Pedido> getPedidosPagados(){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		// Aqui definimos el criterio, buscando los pedidos con status 2, es decir "Pagado"
		criteria.select(root).where(builder.equal(root.get("pedidoStatus"),2));
		
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosPagados = query.getResultList();
		
		return pedidosPagados;
	}
	
	// Funcion para obtener todos los pedidos con Status "Entregado"
	@Override
	public List<Pedido> getPedidosEntregados(){
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		// Aqui definimos el criterio, buscando los pedidos con status 3, es decir "Entregado"
		criteria.select(root).where(builder.equal(root.get("pedidoStatus"),3));
		
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosEntregados = query.getResultList();
		
		return pedidosEntregados;
	}
	
	// Funcion para obtener todos los pedidos con Status "Enviado" y que sean de un usuario en especifico
	@Override
	public List<Pedido> getPedidosEnviadosUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		// Aqui definimos el criterio, buscando los pedidos con status 1, es decir "Enviado",
		//    y que ademas cumpla que sea del usuario en la entrada
		criteria.select(root).where(builder.and(builder.equal(root.get("pedidoStatus"), 1),builder.equal(root.get("usuario"), user)));
		
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosEnviadosUsuario = query.getResultList();
		return pedidosEnviadosUsuario;
	}
	
	
	// Funcion para obtener todos los pedidos con Status "Pagado" y que sean de un usuario en especifico
	@Override
	public List<Pedido> getPedidosPagadosUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		// Aqui definimos el criterio, buscando los pedidos con status 2, es decir "Pagado",
		//    y que ademas cumpla que sea del usuario en la entrada
		criteria.select(root).where(builder.and(builder.equal(root.get("pedidoStatus"), 2),builder.equal(root.get("Usuario"), user)));
		
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosPagadosUsuario = query.getResultList();
		return pedidosPagadosUsuario;
	}
	
	// Funcion para obtener todos los pedidos con Status "Entregado" y que sean de un usuario en especifico
	@Override
	public List<Pedido> getPedidosEntregadosUsuario(Usuario user) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		// Aqui definimos el criterio, buscando los pedidos con status 3, es decir "Entregado",
		//    y que ademas cumpla que sea del usuario en la entrada
		criteria.select(root).where(builder.and(builder.equal(root.get("pedidoStatus"), 3),builder.equal(root.get("Usuario"), user)));
		
		Query<Pedido> query = session.createQuery(criteria);
		List<Pedido> pedidosEntregadosUsuario = query.getResultList();
		return pedidosEntregadosUsuario;
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de pedidos en orden ascendente por id
	@Override
	public List<Pedido> getFirstPedidos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		criteria.select(root);
		
		// Aqui se determina la cantidad maxima de resultados
		Query<Pedido> query = session.createQuery(criteria).setMaxResults(many);
		List<Pedido> pedidos = query.getResultList();

		return pedidos;
	}

	// Funcion para obtener una cantidad, definida por la entrada "many", de pedidos en orden descendente por id
	@Override
	public List<Pedido> getLastPedidos(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Pedido> criteria = builder.createQuery(Pedido.class);
		Root<Pedido> root = criteria.from(Pedido.class);
		
		// Aqui definimos el criterio de ordenarlo descendientemente por id
		criteria.orderBy(builder.desc(root.get("id")));
		
		// Aqui definimos el limite maximo de resultados
		Query<Pedido> query = session.createQuery(criteria).setMaxResults(many);
		
		List<Pedido> pedidos = query.getResultList();

		return pedidos;
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void evict(Pedido pedido) {
		sessionFactory.getCurrentSession().evict(pedido);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void update(Pedido pedido) {
		sessionFactory.getCurrentSession().update(pedido);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void remove(Pedido pedido) {
		sessionFactory.getCurrentSession().remove(pedido);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void save(Pedido pedido) {
		sessionFactory.getCurrentSession().save(pedido);
	}
	
	// Funcion para traer un pedido con un id en especifico
	@Override
	public Pedido getPedido(int id) {
		return sessionFactory.getCurrentSession().get(Pedido.class, id);
	}
}