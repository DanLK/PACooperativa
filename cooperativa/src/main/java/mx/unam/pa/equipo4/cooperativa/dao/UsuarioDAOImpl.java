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
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import mx.unam.pa.equipo4.cooperativa.model.Usuario;
import mx.unam.pa.equipo4.cooperativa.model.Login;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
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
	
	 public Usuario validarUsuario(Login login) {
	    String sql = "select * from usuario where username='" + login.getUsername() + "' and password='" + login.getPassword()
	    + "'";
	    List<Usuario> users = jdbcTemplate.query(sql, new UserMapper());
	    return users.size() > 0 ? users.get(0) : null;
    }
}

class UserMapper implements RowMapper<Usuario> {
	public Usuario mapRow(ResultSet rs, int arg1) throws SQLException {
		Usuario user = new Usuario();
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setNombre(rs.getString("nombre"));
		return user;
	}
}