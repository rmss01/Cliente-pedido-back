package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.IClienteDao;
import com.example.demo.Dominio.Cliente;

@Service
public class ClienteService implements IClienteService{
	
	@Autowired
	private IClienteDao dao;

	@Override
	public void guardar(Cliente c) {
		dao.save(c);
		
	}

	@Override
	public List<Cliente> mostrar() {
		return dao.findAll(Sort.by(Sort.Direction.ASC, "idCliente"));
	}

	@Override
	public void editar(Cliente c) {
		dao.save(c);
	}

	@Override
	public Cliente buscar(Cliente c) {
		return dao.findById(c.getIdCliente()).orElse(null);
	}

	@Override
	public void eliminar(Cliente c) {
		dao.delete(c);
	}

	public Cliente buscarPorNombreYApellido(String nombre, String apellido) {
		return dao.buscarPorNombreYApellido(nombre, apellido);
	}
	
}
