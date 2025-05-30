package com.example.demo.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Dominio.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Integer>{

	// Metodos personalizados ---- creado con java
	@Query(value = "SELECT * FROM CLIENTE WHERE UPPER(NOMBRE) = UPPER(:nombre) AND UPPER(APELLIDO) = UPPER(:apellido)", nativeQuery = true)
	public Cliente buscarPorNombreYApellido(String nombre, String apellido);
}
