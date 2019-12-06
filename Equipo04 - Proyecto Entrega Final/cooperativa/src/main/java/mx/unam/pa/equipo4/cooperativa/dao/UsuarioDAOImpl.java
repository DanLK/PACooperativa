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

// Clase para la implementacion del dao en USUARIO
@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// Funcion para obtener todos los usuarios
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
	
	// Funcion para obtener todos los usuarios con rol de socio
	@Override
	public List<Usuario> getAllSocios() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		// Aqui definimos el criterio, buscando los usuarios con rol 2, es decir "Socio"
		criteria.select(root).where(builder.equal(root.get("rol"),2));
		
		Query<Usuario> query = session.createQuery(criteria);
		List<Usuario> socios = query.getResultList();
		
		return socios;
		
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de usuarios en orden ascendente por id
	@Override
	public List<Usuario> getFirstUsuarios(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		criteria.select(root);
		
		// Aqui definimos la cantidad de resultados
		Query<Usuario> query = session.createQuery(criteria).setMaxResults(many);
		
		List<Usuario> usuarios = query.getResultList();

		return usuarios;
	}

	// Funcion para obtener una cantidad, definida por la entrada "many", de usuarios en orden descendente por id
	@Override
	public List<Usuario> getLastUsuarios(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		// Aqui definimos el criterio de ordenarlo descendientemente por id
		criteria.orderBy(builder.desc(root.get("id")));
		
		// Aqui definimos el limite maximo de resultados
		Query<Usuario> query = session.createQuery(criteria).setMaxResults(many);
		
		List<Usuario> usuarios = query.getResultList();

		return usuarios;
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void evict(Usuario usuario) {
		sessionFactory.getCurrentSession().evict(usuario);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void update(Usuario usuario) {
		sessionFactory.getCurrentSession().update(usuario);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void remove(Usuario usuario) {
		sessionFactory.getCurrentSession().remove(usuario);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void save(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}
	
	// Funcion para traer un usuario con un id en especifico
	@Override
	public Usuario getUsuario(int id) {
		return sessionFactory.getCurrentSession().get(Usuario.class, id);
	}
	
	// Funcion para traer un usuario con un id en especifico
	@Override
	public Usuario getUsuario(String username) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		
		Root<Usuario> root = criteria.from(Usuario.class);
		
		// Aqui definimos el criterio de coincidencia para usuario y contraseña
		criteria.select(root).where(builder.and(
				builder.equal(root.get("username"), username)
			));
		
		Query<Usuario> query = session.createQuery(criteria);
	    List<Usuario> users = query.getResultList();
	    if (users.size() == 0) {
	    	return null;
	    }
	    return users.get(0);
	}
	
	// Funcion para validar el usuario y contraseña para logearse
	@Override
	public Usuario validarUsuario(LoginFrm login) {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		
		Root<Usuario> root = criteria.from(Usuario.class);
		
		// Aqui definimos el criterio de coincidencia para usuario y contraseña
		criteria.select(root).where(builder.and(
				builder.equal(root.get("username"), login.getUsername()),
				builder.equal(root.get("password"), login.getPassword())
			));
		
		Query<Usuario> query = session.createQuery(criteria);
	    List<Usuario> users = query.getResultList();
	    return users.size() > 0 ? users.get(0) : null;
    }
	
}