package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.PedidoStatusDAO;
import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;

// Clase para la implementacion del service en PEDIDO_STATUS
@Service
@Transactional
public class PedidoStatusServiceImpl implements PedidoStatusService {
	@Autowired
	private PedidoStatusDAO dao;
	
	// Funcion para obtener todos los status
	@Override
	public List<PedidoStatus> listarPedidoStatus() {
		return dao.getAllPedidoStatus();
	}
	
	// Funcion para obtener una cantidad, definida por la entrada "many", de status en orden descendente por id
	@Override
	public List<PedidoStatus> listarUltimosPedidoStatus(int many) {
		return dao.getLastPedidoStatus(many);
	}

	// Funcion para "desalojar" un objeto de la sesion de hibernate
	@Override
	public void desalojar(PedidoStatus pedidoStatus) {
		dao.evict(pedidoStatus);
	}
	
	// Funcion para actualizar el objeto de la sesion de hibernate y devolverlo al contexto persistente
	@Override
	public void actualizar(PedidoStatus pedidoStatus) {
		dao.update(pedidoStatus);
	}
	
	// Funcion para remover un objeto de la sesion de hibernate y del contexto persistente
	@Override
	public void eliminar(PedidoStatus pedidoStatus) {
		dao.remove(pedidoStatus);
	}
	
	// Funcion para guardar un objeto en la sesion de hibernate y el contexto persistente
	@Override
	public void guardar(PedidoStatus pedidoStatus) {
		dao.save(pedidoStatus);
	}
	
	// Funcion para traer un status con un id en especifico
	@Override
	public PedidoStatus getPedidoStatus(int id) {
		return dao.getPedidoStatus(id);
	}
}
