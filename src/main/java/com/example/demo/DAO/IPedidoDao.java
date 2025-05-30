package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Dominio.Cliente;
import com.example.demo.Dominio.Pedido;

public interface IPedidoDao extends JpaRepository<Pedido, Integer>{

	//Metodo de consulta personalizado
	@Query(value = "SELECT * FROM PEDIDO WHERE UPPER(STATUS) = UPPER(:estatus)", nativeQuery = true)
	public List<Pedido> obtenerPedidosPorEstatus(String estatus);
	
	@Query(value = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = :clienteId", nativeQuery = true)
	public Cliente obtenerClienteById(int clienteId);
}
