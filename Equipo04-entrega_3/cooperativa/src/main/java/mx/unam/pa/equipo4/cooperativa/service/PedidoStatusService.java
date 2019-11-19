package mx.unam.pa.equipo4.cooperativa.service;

import java.util.List;

import mx.unam.pa.equipo4.cooperativa.model.PedidoStatus;

public interface PedidoStatusService {
	public List<PedidoStatus> listarPedidoStatus();
	public List<PedidoStatus> listarUltimosPedidoStatus(int many);
	public void desalojar(PedidoStatus pedidoStatus);
	public void actualizar(PedidoStatus pedidoStatus);
	public void eliminar(PedidoStatus pedidoStatus);
	public void guardar(PedidoStatus pedidoStatus);
	public PedidoStatus getPedidoStatus(int id);
}