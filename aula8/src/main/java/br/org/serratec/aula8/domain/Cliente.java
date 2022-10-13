package br.org.serratec.aula8.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	@ApiModelProperty(value = "Identificador unico do cliente")
	private Long id;

	@Size(max = 60, message = "Tamanho máximo 60")
	@Column(name = "nome", nullable = true, length = 60)
	@ApiModelProperty(value = "Nome do cliente", required = true)
	private String nome;

	@CPF(message = "CPF Inválido")
	@Size(max = 11, message = "Tamanho máximo 11 caracteres")
	@Column
	@ApiModelProperty(value = "CPF do cliente", required = true)
	private String cpf;

	@Email(message = "Email inválido")
	@Column
	@ApiModelProperty(value = "Email do cliente", required = true)
	private String email;

	@Past(message = "Data de nascimento inválida")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento", nullable = false)
	@ApiModelProperty(value = "Data de nascimento do cliente", required = true)
	private LocalDate dataNascimento;

	@Embedded
	@ApiModelProperty(value = "Endereco do cliente")
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
