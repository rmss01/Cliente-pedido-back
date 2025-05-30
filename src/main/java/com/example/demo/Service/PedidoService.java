package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IPedidoDao;
import com.example.demo.Dominio.Cliente;
import com.example.demo.Dominio.Pedido;

@Service
public class PedidoService implements IPedidoService{
	
	@Autowired
	private IPedidoDao dao;

	@Override
	public void guardar(Pedido p) {
		dao.save(p);
	}

	@Override
	public List<Pedido> mostrar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idPedido"));
	}

	@Override
	public void editar(Pedido p) {
		dao.save(p);
	}

	@Override
	public Pedido buscar(Pedido p) {
		return dao.findById(p.getIdPedido()).orElse(null);
	}

	@Override
	public void eliminar(Pedido p) {
		dao.delete(p);
		
	}
	
	public List<Pedido> buscarPorStatus(String status){
		return dao.obtenerPedidosPorEstatus(status);
	}
	
	public Cliente obtenerClienteById(int clienteId) {
		return dao.obtenerClienteById(clienteId);
	}

}
