package br.org.serratec.trabalho.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column // (name = "titulo")
	@NotBlank(message = "título deve ser preenchido")
	@ApiModelProperty(value = "Título do livro")
	private String titulo;

//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
	@NotNull
	@JoinColumn(name = "categoria") // id_categoria
	@ApiModelProperty(value = "Categoria do livro")
	private Categoria categoria;

	@Column // (name = "isbn")
	@NotBlank(message = "Isbn deve ser preenchido")
	@ApiModelProperty(value = "ISBN do livro")
	private String isbn;

	@Column(name = "data_publicacao")
	@NotNull(message = "dataPublicacao deve ser preenchido")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@ApiModelProperty(value = "Data de publicação do livro")
	private LocalDate dataPublicacao;

	@ManyToMany
	@JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "id_livro"), inverseJoinColumns = @JoinColumn(name = "id_autor"))
	private List<Autor> autores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

}
