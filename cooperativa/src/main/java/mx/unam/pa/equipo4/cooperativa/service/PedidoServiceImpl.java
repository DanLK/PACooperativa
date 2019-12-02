package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.PedidoDAO;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

// Clase para la implementacion del Service en PEDIDO
@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {
	@Autowired
	private PedidoDAO dao;
	
	// Funcion para obtener TODOS los pedidos de la base de datos
	@Override
	public List<Pedido> listarPedidos() {
		return dao.getAllPedidos();
	}
	
	// Funcion para obtener los pedidos entre el rango entre la fecha actual y hace una semana
	@Override
	public List<Pedido> listarPedidosSemana() {
		return dao.getPedidosSemana();
	}
	
	// Funcion para obtener todos los pedidos asignados a un usuario en especifico
	@Override
	public List<Pedido> listarPedidosUsuario(Usuario user) {
		return dao.getPedidosUsuario(user);
	}
	
	// Funcion para obtener todos los pedidos con Status de "Enviado"
	@Override
	public List<Pedido> listarPedidosEnviados() {
		return dao.getPedidosEnviados();
	}
	
	// Funcion para obtener todos los pedidos con Status "Pagado"
	@Override
	public List<Pedido> listarPedidosPagados() {
		return dao.getPedidosPagados();
	}
	
	// Funcion para obtener todos los pedidos con Status "Entregado"
	@Override
	public List<Pedido> listarPedidosEntregados() {
		return dao.getPedidosEntregados();
	}
	
	// Funcion para obtener todos los pedidos con Status "Enviado" y que sean de un usuario en especifico
	@Override
	public List<Pedido> listarPedidosEnviadosUsuarios(Usuario user){
		return dao.getPedidosEnviadosUsuario(user);
	}
	
	// Funcion para obtener todos los pedidos con Status "Pagado" y que sean de un usuario en especifico
	@Override
	public List<Pedido> listarPedidosPagadosUsuarios(Usuario user){
		return dao.getPedidosPagadosUsuario(user);
	}
	
	// Funcion para obtener todos los pedidos con Status "Entregado" y que sean de un usuario en especifico
	@Override
	public List<Pedido> listarPedidosEntregadosUsuarios(Usuario user){
		return dao.getPedidosEntregadosUsuario(user);
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de pedidos en orden ascendente por id
	@Override
	public List<Pedido> listarPrimerosPedidos(int many) {
		return dao.getFirstPedidos(many);
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de pedidos en orden descendente por id
	@Override
	public List<Pedido> listarUltimosPedidos(int many) {
		return dao.getLastPedidos(many);
	}
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void desalojar(Pedido pedido) {
		dao.evict(pedido);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void actualizar(Pedido pedido) {
		dao.update(pedido);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void eliminar(Pedido pedido) {
		dao.remove(pedido);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void guardar(Pedido pedido) {
		dao.save(pedido);
	}
	
	// Funcion para traer un pedido con un id en especifico
	@Override
	public Pedido getPedido(int id) {
		return dao.getPedido(id);
	}
	
}
