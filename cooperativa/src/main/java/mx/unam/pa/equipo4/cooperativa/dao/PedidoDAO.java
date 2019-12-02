package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.Pedido;
import mx.unam.pa.equipo4.cooperativa.model.Usuario;

// Clase para la definicion del dao en PEDIDO
public interface PedidoDAO {
	
	// Funcion para obtener TODOS los pedidos de la base de datos
	public List<Pedido> getAllPedidos();
	
	// Funcion para obtener los pedidos entre el rango entre la fecha actual y hace una semana
	public List<Pedido> getPedidosSemana();
	
	// Funcion para obtener todos los pedidos asignados a un usuario en especifico
	public List<Pedido> getPedidosUsuario(Usuario user);
	
	// Funcion para obtener todos los pedidos con Status de "Enviado"
	public List<Pedido> getPedidosEnviados();
	
	// Funcion para obtener todos los pedidos con Status "Pagado"
	public List<Pedido> getPedidosPagados();
	
	// Funcion para obtener todos los pedidos con Status "Entregado"
	public List<Pedido> getPedidosEntregados();
	
	// Funcion para obtener todos los pedidos con Status "Enviado" y que sean de un usuario en especifico
	public List<Pedido> getPedidosEnviadosUsuario(Usuario user);
	
	// Funcion para obtener todos los pedidos con Status "Pagado" y que sean de un usuario en especifico
	public List<Pedido> getPedidosPagadosUsuario(Usuario user);
	
	// Funcion para obtener todos los pedidos con Status "Entregado" y que sean de un usuario en especifico
	public List<Pedido> getPedidosEntregadosUsuario(Usuario user);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de pedidos en orden ascendente por id
	public List<Pedido> getFirstPedidos(int many);
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de pedidos en orden descendente por id
	public List<Pedido> getLastPedidos(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void evict(Pedido pedido);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void update(Pedido pedido);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void remove(Pedido pedido);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void save(Pedido pedido);
	
	// Funcion para traer un pedido con un id en especifico
	public Pedido getPedido(int id);
	
}