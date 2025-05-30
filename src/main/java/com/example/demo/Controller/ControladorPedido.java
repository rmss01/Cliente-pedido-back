package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dominio.Cliente;
import com.example.demo.Dominio.Pedido;
import com.example.demo.Service.PedidoService;


@RestController
@RequestMapping(path = "enucom/pedido")
@CrossOrigin
public class ControladorPedido {
	
	@Autowired
	private PedidoService service;

	@PostMapping("/guardar")
	public ResponseEntity<?> guardar(@RequestBody Pedido p){
		if(service.buscar(p) == null) { 
			int clienteId = p.getClienteID().getIdCliente();
			Cliente establecerCli = service.obtenerClienteById(clienteId);
			if(establecerCli != null) {
				service.guardar(p);
				return ResponseEntity.status(HttpStatus.CREATED).body("{\"mensaje\": \"Se ha creado correctamente el pedido " + p.getIdPedido() + ".\"}");
			} else
				return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\": \"No existe un cliente con el id: " + clienteId + ".\"}");
			
		} else
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"mensaje\": \"Ya existe un pedido con el id: " + p.getIdPedido() + ".\"}");
	}
	
	@GetMapping("/mostrar")
	public ResponseEntity<?> mostrar(){
		if(service.mostrar().isEmpty())
			return ResponseEntity.noContent().build();
		else 
			return ResponseEntity.ok(service.mostrar());
	}
	
	@PutMapping("/editar")
	public ResponseEntity<?> editar(@RequestBody Pedido p){
		if(service.buscar(p) != null) {
			service.editar(p);
			return ResponseEntity.status(HttpStatus.OK).body("{\"mensaje\": \"Se ha editado el pedido: " + p.getIdPedido() + ".\"}");
		} else
			return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<?> buscar(@RequestBody Pedido p) {
		Pedido encontrado = service.buscar(p);
		if(service.mostrar().isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{\"mensaje\": \"No hay pedidos que buscar\"}");
		else if(encontrado == null)
			return ResponseEntity.notFound().build();
		else 
			return ResponseEntity.ok(encontrado);
	}
	
	@DeleteMapping("/eliminar")
	public ResponseEntity<?> eliminar(Pedido p){
		service.eliminar(p);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/buscarPorEstatus")
	public ResponseEntity<?> buscarPorStatus(@RequestBody Pedido p) {
		List<Pedido> encontrados = service.buscarPorStatus(p.getStatus());
		if(!encontrados.isEmpty())
			return ResponseEntity.ok(encontrados);
		else
			return ResponseEntity.noContent().build();
	}
	
}
