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
	public void save(Usuario usuario) {
		sessionFactory.getCurrentSession().save(usuario);
	}
	
	@Override
	public Usuario getUsuario(int id) {
		return sessionFactory.getCurrentSession().get(Usuario.class, id);
	}
}