package br.org.serratec.ecommerce.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long id;

	@Size(min = 3, max = 30, message = "Nome do produto deve ser entre {min} e {max} caracteres")
	@NotBlank(message = "Preencha o nome")
	@Column(nullable = false, length = 30, unique = true)
	private String nome;

	@Size(min = 3, max = 200, message = "Descriçao deve ser entre {min} e {max} caracteres")
	@Column(nullable = true, length = 200)
	private String descricao;

	@NotNull(message = "Quantidade de estoque deve ser preenchido")
	@Column(nullable = false, name = "qtd_estoque")
	private Integer qtdEstoque;

//	@NotBlank(message = "Data de cadastro deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(nullable = false, name = "data_cadastro ")
	private LocalDate dataCadastro;

	@NotNull(message = "Valor unitário deve ser preenchido")
	@Column(nullable = false, name = "valor_unitario")
	private Double valorUnitario;

	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(nullable = false)
	private Byte[] imagem;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_categoria", nullable = false)
	private Categoria categoria;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Byte[] getImagem() {
		return imagem;
	}

	public void setImagem(Byte[] imagem) {
		this.imagem = imagem;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}

}
