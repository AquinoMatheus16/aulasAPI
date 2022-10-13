package br.org.serratec.h2exercicio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Inheritance(strategy=InheritanceType.JOINED)
@Entity
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(length = 50)
	@NotBlank(message = "Nome da Pessoa deve ser preenchido")
	@Size(min = 2, max = 50, message = "Nome da pessoa deve ter entre {min} e {max} letras")
	protected String nome;

	@CPF(message = "CPF Inválido")
	@Size(max = 11, message = "Tamanho máximo 11 caracteres")
	protected String cpf;

	@Column
	protected Double salario;

	@Column
	protected String turno;

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

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

}
