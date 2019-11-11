package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.pa.equipo4.cooperativa.dao.PedidoStatusDAO;
import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;

@Service
@Transactional
public class PedidoStatusServiceImpl implements PedidoStatusService {
	@Autowired
	private PedidoStatusDAO dao;
	
	@Override
	public List<PedidoStatus> listarPedidoStatus() {
		return dao.getAllPedidoStatus();
	}
	
	@Override
	public List<PedidoStatus> listarUltimosPedidoStatus(int many) {
		return dao.getLastPedidoStatus(many);
	}

	@Override
	public void desalojar(PedidoStatus pedidoStatus) {
		dao.evict(pedidoStatus);
	}
	
	@Override
	public void actualizar(PedidoStatus pedidoStatus) {
		dao.update(pedidoStatus);
	}
	
	@Override
	public void eliminar(PedidoStatus pedidoStatus) {
		dao.remove(pedidoStatus);
	}
	
	@Override
	public void guardar(PedidoStatus pedidoStatus) {
		dao.save(pedidoStatus);
	}
	
	@Override
	public PedidoStatus getPedidoStatus(int id) {
		return dao.getPedidoStatus(id);
	}
}
