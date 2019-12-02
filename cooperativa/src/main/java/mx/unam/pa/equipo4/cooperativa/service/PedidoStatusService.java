package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;

// Clase para la definicion del dao en PEDIDO_STATUS
public interface PedidoStatusService {
	
	// Funcion para obtener todos los status
	public List<PedidoStatus> listarPedidoStatus();
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de status en orden descendente por id
	public List<PedidoStatus> listarUltimosPedidoStatus(int many);
	
	// Funcion para "desalojar" un objeto de la sesion de hibernate
	public void desalojar(PedidoStatus pedidoStatus);
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	public void actualizar(PedidoStatus pedidoStatus);
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	public void eliminar(PedidoStatus pedidoStatus);
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	public void guardar(PedidoStatus pedidoStatus);
	
	// Funcion para traer un status con un id en especifico
	public PedidoStatus getPedidoStatus(int id);
	
}