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

import mx.unam.pa.equipo4.cooperativa.model.Rol;

// Clase para la implementacion del dao en ROL
@Repository
public class RolDAOImpl implements RolDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// Funcion para obtener todos los roles
	@Override
	public List<Rol> getAllRoles() {
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Rol> criteria = builder.createQuery(Rol.class);
		
		Root<Rol> root = criteria.from(Rol.class);
		criteria.select(root);
		
		Query<Rol> query = session.createQuery(criteria);
		List<Rol> roles = query.getResultList();
		
		return roles;
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de roles en orden descendente por id
	@Override
	public List<Rol> getLastRoles(int many) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Rol> criteria = builder.createQuery(Rol.class);
		Root<Rol> root = criteria.from(Rol.class);
		
		// Aqui definimos el criterio de ordenarlo descendientemente por id
		criteria.orderBy(builder.desc(root.get("id")));
		
		// Aqui definimos el limite maximo de resultados
		Query<Rol> query = session.createQuery(criteria).setMaxResults(many);
		
		List<Rol> roles = query.getResultList();

		return roles;
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void evict(Rol rol) {
		sessionFactory.getCurrentSession().evict(rol);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void update(Rol rol) {
		sessionFactory.getCurrentSession().update(rol);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void remove(Rol rol) {
		sessionFactory.getCurrentSession().remove(rol);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void save(Rol rol) {
		sessionFactory.getCurrentSession().save(rol);
	}
	
	// Funcion para traer un rol con un id en especifico
	@Override
	public Rol getRol(int id) {
		return sessionFactory.getCurrentSession().get(Rol.class, id);
	}
}