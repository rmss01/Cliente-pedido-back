package com.example.demo.Dominio;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

	@Id
	@Column(name = "ID_PEDIDO")
	private int idPedido;
	
	@Column(name = "FECHA")
	private Date fecha;
	
	@Column(name = "TOTAL")
	private double total;
	
	@Column(name = "STATUS")
	private String status;
	
	@JoinColumn(name = "CLIENTE_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente clienteID; // Mostrar los clientes con todos sus atributos
	
	public Pedido() {}

	public Pedido(int idPedido, Date fecha, double total, String status, Cliente clienteID) {
		super();
		this.idPedido = idPedido;
		this.fecha = fecha;
		this.total = total;
		this.status = status;
		this.clienteID = clienteID;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getClienteID() {
		return clienteID;
	}

	public void setClienteID(Cliente clienteID) {
		this.clienteID = clienteID;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", fecha=" + fecha + ", total=" + total + ", status=" + status
				+ ", clienteID=" + clienteID + "]";
	}
	
	
}
