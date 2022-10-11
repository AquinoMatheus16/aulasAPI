package br.org.serratec.aula6.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {

	@Size(max = 50, message = "Tamanho máximo 50")
	@Column(name = "logradouro", nullable = true, length = 50)
	private String logradouro;

	@Size(max = 6, message = "Tamanho máximo 6")
	@Column(name = "numero", nullable = true, length = 6)
	private String numero;

	@Size(max = 40, message = "Tamanho máximo 40")
	@Column(name = "bairro", nullable = true, length = 40)
	private String bairro;

	@Size(max = 40, message = "Tamanho máximo 40")
	@Column(name = "cidade", nullable = true, length = 40)
	private String cidade;

	@Size(max = 30, message = "Tamanho máximo 30")
	@Column(name = "estado", nullable = true, length = 30)
	private String estado;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
