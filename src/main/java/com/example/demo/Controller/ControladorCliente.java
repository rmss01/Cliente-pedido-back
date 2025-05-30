package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dominio.Cliente;
import com.example.demo.Service.ClienteService;
import com.example.demo.Service.IClienteService;

@RestController
@RequestMapping(path = "enucom/cliente")
@CrossOrigin
public class ControladorCliente {

	@Autowired
	private ClienteService service;
	
	@GetMapping("/mostrar")
	public ResponseEntity<?> mostrar(){
		List<Cliente> clientes = service.mostrar();
		if (clientes.isEmpty())
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.ok(clientes);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@RequestBody Cliente c){
		if(service.buscar(c) == null) {
			service.guardar(c);
			return ResponseEntity.status(HttpStatus.CREATED).body("{\"mensaje\": \"Se ha creado el cliente: " + c.getIdCliente() + ".\"}");
		} else
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\": \"Ya existe un cliente con id: " + c.getIdCliente() + ".\"}");
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@RequestBody Cliente c){
		if(service.buscar(c) != null) {
			service.editar(c);
			return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\": \"Se ha modificado el cliente: " + c.getIdCliente() + ".\"}");
		} else
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\": \"No se ha modificado el cliente: " + c.getIdCliente() + " porque el cliente no existe.\"}"); //"No se ha modificado el cliente " + c.getIdCliente() + " porque el cliente no existe"
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscar(@RequestBody Cliente c){
		Cliente encontrado = service.buscar(c);
		if (encontrado != null) {
			return ResponseEntity.ok(encontrado);
		} else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"mensaje\": \"No se encontro ningun cliente con id " + c.getIdCliente() + ".\"}");
	}
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<?> eliminar(@RequestBody Cliente c){
		service.eliminar(c);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/nombreYapellido")
	public ResponseEntity<?> buscarN(@RequestParam String nombre, @RequestParam String apellido){
		Cliente encontrado = service.buscarPorNombreYApellido(nombre, apellido);
		if(encontrado == null)
			return ResponseEntity.notFound().build();
		else 
			return ResponseEntity.ok(encontrado);
	}
}
