package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

// Clase para la definicion del Service en PEDIDO
public interface PedidoService {
	
	// Funcion para obtener TODOS los pedidos de la base de datos
	public List<Pedido> listarPedidos();
	
	// Funcion para obtener los pedidos entre el rango entre la fecha actual y hace una semana
	public List<Pedido> listarPedidosSemana();
	
	// Funcion para obtener todos los pedidos asignados a un usuario en especifico
	public List<Pedido> listarPedidosUsuario(Usuario user);
	
	// Funcion para obtener todos los pedidos con Status de "Enviado"
	public List<Pedido> listarPedidosEnviados();
	
	// Funcion para obtener todos los pedidos con Status "Pagado"
	public List<Pedido> listarPedidosPagados();
	
	// Funcion para obtener todos los pedidos con Status "Entregado"
	public List<Pedido> listarPedidosEntregados();
	
	// Funcion para obtener todos los pedidos con Status "Enviado" y que sean de un usuario en especifico
	public List<Pedido> listarPedidosEnviadosUsuarios(Usuario user);
	
	// Funcion para obtener todos los pedidos con Status "Pagado" y que sean de un usuario en especifico
	public List<Pedido> listarPedidosPagadosUsuarios(Usuario user);
	
	// Funcion para obtener todos los pedidos con Status "Entregado" y que sean de un usuario en especifico
	public List<Pedido> listarPedidosEntregadosUsuarios(Usuario user);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de pedidos en orden ascendente por id
	public List<Pedido> listarPrimerosPedidos(int many);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de pedidos en orden descendente por id
	public List<Pedido> listarUltimosPedidos(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void desalojar(Pedido pedido);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void actualizar(Pedido pedido);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void eliminar(Pedido pedido);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void guardar(Pedido pedido);
	
	// Funcion para traer un pedido con un id en especifico
	public Pedido getPedido(int id);
	
}