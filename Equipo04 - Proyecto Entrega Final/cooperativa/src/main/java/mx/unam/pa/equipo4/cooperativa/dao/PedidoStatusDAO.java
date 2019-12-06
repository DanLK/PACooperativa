package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;

// Clase para la definicion del dao en PEDIDO_STATUS
public interface PedidoStatusDAO {
	
	// Funcion para obtener todos los status
	public List<PedidoStatus> getAllPedidoStatus();
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de status en orden descendente por id
	public List<PedidoStatus> getLastPedidoStatus(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void evict(PedidoStatus pedidoStatus);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void update(PedidoStatus pedidoStatus);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void remove(PedidoStatus pedidoStatus);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void save(PedidoStatus pedidoStatus);
	
	// Funcion para traer un status con un id en especifico
	public PedidoStatus getPedidoStatus(int id);
	
}