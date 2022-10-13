package br.org.serratec.aula8.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	@ApiModelProperty(value = "Identificador unico do pedido")
	private Long id;

	@Column(name = "data_pedido")
	@ApiModelProperty(value = "Data do pedido", required = true)
	private LocalDate dataPedido;

	@Column(name = "hora_pedido")
	@ApiModelProperty(value = "Hora do pedido", required = true)
	private LocalTime horaPedido;

	@Column(name = "data_entrega")
	@ApiModelProperty(value = "Data da entrega do pedido", required = true)
	private LocalDate dataEntrega;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	@ApiModelProperty(value = "Dados do cliente")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalTime getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(LocalTime horaPedido) {
		this.horaPedido = horaPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
