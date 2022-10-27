package br.org.serratec.ecommerce.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDTO {

	@NotBlank(message = "Nome completo deve ser preenchido")
	@Column(name = "nome_completo", nullable = false, length = 50)
	@Size(max = 50, message = "Nome completo não deve passar de {max} caracteres")
	private String nomeCompleto;

	@NotBlank(message = "CPF deve ser preenchido")
	@Column(name = "cpf", nullable = false, length = 11, unique = true)
	@Size(max = 11, message = "CPF não deve passar de {max} caracteres")
	@CPF(message = "CPF inválido")
	private String cpf;

	@NotBlank(message = "Telefone deve ser preenchido")
	@Column(name = "telefone", nullable = false, length = 40)
	@Size(max = 40, message = "Telefone não deve passar de {max} caracteres")
	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento", nullable = false)
	private String dataNascimento;

	private String cep;

	private String numero;

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
