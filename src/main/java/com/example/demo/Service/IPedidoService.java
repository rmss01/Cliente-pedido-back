package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dominio.Pedido;


public interface IPedidoService {

	public void guardar(Pedido p);
	
	public List<Pedido> mostrar();
	
	public void editar(Pedido p);
	
	public Pedido buscar(Pedido p);
	
	public void eliminar(Pedido p);
}
