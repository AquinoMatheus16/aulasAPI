package br.org.serratec.ecommerce.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "endereco")
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long id;

	@NotBlank(message = "Cep deve ser preenchido")
	@Size(max = 8, message = "Cep não deve passar de {max} caracteres")
	@Column(name = "cep", length = 8, nullable = false)
	private String cep;

	@NotBlank(message = "Rua deve ser preenchido")
	@Size(max = 80, message = "Rua não deve passar de {max} caracteres")
	@Column(name = "rua", length = 80, nullable = false)
	private String rua;

	@NotBlank(message = "Bairro deve ser preenchido")
	@Size(max = 50, message = "Bairro não deve passar de {max} caracteres")
	@Column(name = "bairro", length = 50, nullable = false)
	private String bairro;

	@NotBlank(message = "Cidade deve ser preenchido")
	@Size(max = 80, message = "Cidade não deve passar de {max} caracteres")
	@Column(name = "cidade", length = 80, nullable = false)
	private String cidade;

	@NotBlank(message = "Número deve ser preenchido")
	@Size(max = 20, message = "Número não pode passar de {max} caracteres")
	@Column(name = "numero", length = 20, nullable = false)
	private String numero;

	@Size(max = 80, message = "Complemento não deve passar de {max} caracteres")
	@Column(name = "complemento", length = 80, nullable = true)
	private String complemento;

	@NotBlank(message = "uf deve ser preenchido")
	@Size(max = 2, min = 2, message = "uf deve ser 2 caracteres")
	@Column(name = "uf", length = 2, nullable = false)
	private String uf;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(id, other.id);
	}
}

//	{
//	    "nomeCompleto":"Matheus de Aquino",
//	    "email":"mathesu@gmail.com",
//	    "cpf":"23514098255",
//	    "telefone":"22999243318",
//	    "dataNascimento":"17/04/2003",
//	    "endereco":{
//	        "cep":"13973188",
//	        "rua":"Rua Adolfo Ricciluca",
//	        "cidade":"Itapira",
//	        "bairro":"Parque São Jorge",
//	        "numero":"300",
//	        "complemento":"",
//	        "uf":"SP"
//	    }
//	}
