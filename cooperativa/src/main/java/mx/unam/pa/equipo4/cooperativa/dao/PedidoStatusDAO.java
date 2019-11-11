package mx.unam.pa.equipo4.cooperativa.dao;

import java.util.List;
import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;

public interface PedidoStatusDAO {
	
	public List<PedidoStatus> getAllPedidoStatus();
	
	public List<PedidoStatus> getLastPedidoStatus(int many);
	
	public void evict(PedidoStatus pedidoStatus);
	
	public void update(PedidoStatus pedidoStatus);
	
	public void remove(PedidoStatus pedidoStatus);
	
	public void save(PedidoStatus pedidoStatus);
	
	public PedidoStatus getPedidoStatus(int id);
	
}