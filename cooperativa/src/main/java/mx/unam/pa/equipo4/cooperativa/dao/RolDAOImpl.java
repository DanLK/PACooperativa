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

@Repository
public class RolDAOImpl implements RolDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
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
	
	@Override
	public void save(Rol rol) {
		sessionFactory.getCurrentSession().save(rol);
	}
	
	@Override
	public Rol getRol(int id) {
		return sessionFactory.getCurrentSession().get(Rol.class, id);
	}
}