package com.example.demo.Service;

import java.util.List;

import com.example.demo.Dominio.Cliente;

public interface IClienteService {

	public void guardar(Cliente c);
	
	public List<Cliente> mostrar();
	
	public void editar(Cliente c);
	
	public Cliente buscar(Cliente c);
	
	public void eliminar(Cliente c);
}
