package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.PedidoDAO;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {
	@Autowired
	private PedidoDAO dao;
	
	@Override
	public List<Pedido> listarPedidos() {
		return dao.getAllPedidos();
	}
	
	@Override
	public List<Pedido> listarPedidosSemana() {
		return dao.getPedidosSemana();
	}
	
	@Override
	public List<Pedido> listarPedidosUsuario(Usuario user) {
		return dao.getPedidosUsuario(user);
	}
	
	@Override
	public List<Pedido> listarPedidosEnviados() {
		return dao.getPedidosEnviados();
	}
	
	@Override
	public List<Pedido> listarPedidosPagados() {
		return dao.getPedidosPagados();
	}
	
	@Override
	public List<Pedido> listarPedidosEntregados() {
		return dao.getPedidosEntregados();
	}
	
	@Override
	public List<Pedido> listarPedidosEnviadosUsuarios(Usuario user){
		return dao.getPedidosEnviadosUsuario(user);
	}
	
	@Override
	public List<Pedido> listarPedidosPagadosUsuarios(Usuario user){
		return dao.getPedidosPagadosUsuario(user);
	}
	
	@Override
	public List<Pedido> listarPedidosEntregadosUsuarios(Usuario user){
		return dao.getPedidosEntregadosUsuario(user);
	}

	@Override
	public List<Pedido> listarPrimerosPedidos(int many) {
		return dao.getFirstPedidos(many);
	}
	
	@Override
	public List<Pedido> listarUltimosPedidos(int many) {
		return dao.getLastPedidos(many);
	}
	
	@Override
	public void desalojar(Pedido pedido) {
		dao.evict(pedido);
	}
	
	@Override
	public void eliminar(Pedido pedido) {
		dao.remove(pedido);
	}
	
	@Override
	public void actualizar(Pedido pedido) {
		dao.update(pedido);
	}
	
	@Override
	public void guardar(Pedido pedido) {
		dao.save(pedido);
	}
	
	@Override
	public Pedido getPedido(int id) {
		return dao.getPedido(id);
	}
	
}
