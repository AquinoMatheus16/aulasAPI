package br.org.serratec.ecommerce.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;

	@NotBlank(message = "Nome completo deve ser preenchido")
	@Column(name = "nome_completo", nullable = false, length = 50)
	@Size(max = 50, message = "Nome completo não deve passar de {max} caracteres")
	private String nomeCompleto;

	@NotBlank(message = "E-mail deve ser preenchido")
	@Column(name = "email", nullable = false, length = 80, unique = true)
	@Size(max = 80, message = "E-mail não deve passar de {max} caracteres")
	@Email(message = "E-mail inválido")
	private String email;

	@NotBlank(message = "CPF deve ser preenchido")
	@Column(name = "cpf", nullable = false, length = 11, unique = true)
	@Size(max = 11, message = "CPF não deve passar de {max} caracteres")
	@CPF(message = "CPF inválido")
	private String cpf;

	@NotBlank(message = "Telefone deve ser preenchido")
	@Column(name = "telefone", nullable = false, length = 40)
	@Size(max = 40, message = "Telefone não deve passar de {max} caracteres")
	private String telefone;

//	@NotBlank(message = "Data de nascimento deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

//	@NotNull(message = "Endereco deve ser preenchido")
	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}

}
