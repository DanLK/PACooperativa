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

import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.formas.LoginFrm;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Usuario> getAllUsuarios() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(root);
		
		Query<Usuario> query = session.createQuery(criteria);
		List<Usuario> usuarios = query.getResultList();
		
		return usuarios;
	}
	
	@Override
	public List<Usuario> getAllSocios() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(root).where(builder.equal(root.get("rol"),2));
		Query<Usuario> query = session.createQuery(criteria);
		List<Usuario> socios = query.getResultList();
		
		return socios;
		
	}
	
	@Override
	public List<Usuario> getFirstUsuarios(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(root);
		Query<Usuario> query = session.createQuery(criteria).setMaxResults(many);
		List<Usuario> usuarios = query.getResultList();

		return usuarios;
	}

	@Override
	public List<Usuario> getLastUsuarios(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.orderBy(builder.desc(root.get("id")));
		Query<Usuario> query = session.createQuery(criteria).setMaxResults(many);
		List<Usuario> usuarios = query.getResultList();

		return usuarios;
	}
	
	@Override
	public void evict(Usuario usuario) {
		sessionFactory.getCurrentSession().evict(usuario);
	}
	
	@Override
	public void update(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}
	
	@Override
	public void remove(Usuario usuario) {
		sessionFactory.getCurrentSession().remove(usuario);
	}
	
	@Override
	public void save(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}
	
	@Override
	public Usuario getUsuario(int id) {
		return sessionFactory.getCurrentSession().get(Usuario.class, id);
	}
	
	@Override
	public Usuario validarUsuario(LoginFrm login) {
		 Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(root).where(builder.and(
				builder.equal(root.get("username"), login.getUsername()),
				builder.equal(root.get("password"), login.getPassword())
			));
		
		Query<Usuario> query = session.createQuery(criteria);
	    List<Usuario> users = query.getResultList();
	    return users.size() > 0 ? users.get(0) : null;
    }
	
}