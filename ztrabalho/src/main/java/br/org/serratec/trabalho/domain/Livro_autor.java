package br.org.serratec.trabalho.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//public class Livro_autor implements Serializable {
@Entity
public class Livro_autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;

	@ManyToOne
	@JoinColumn(name = "id_autor")
	private Autor autor;
	
//	@ManyToMany
//	@JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "id_autor"), inverseJoinColumns = @JoinColumn(name = "id_livro"))
	
//	@ManyToMany
//	@JoinTable(name = "livro_autor", joinColumns = @JoinColumn(name = "id_livro"), inverseJoinColumns = @JoinColumn(name = "id_autor"))

	public Livro_autor(Livro livro, Autor autor) {
		super();
		this.livro = livro;
		this.autor = autor;
	}

	public Livro_autor() {
		super();
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

}
