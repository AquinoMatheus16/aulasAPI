package br.org.serratec.trabalho.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Emprestimo_Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_emprestimo")
	private Emprestimo emprestimo;

	@ManyToOne
	@JoinColumn(name = "id_livro")
	private Livro livro;

	@Column(length = 200, nullable = false)
	private String observacoesAtoEmprestimo;

	public Emprestimo_Livro(Long id, Emprestimo emprestimo, Livro livro, String observacoesAtoEmprestimo) {
		super();
		this.id = id;
		this.emprestimo = emprestimo;
		this.livro = livro;
		this.observacoesAtoEmprestimo = observacoesAtoEmprestimo;
	}

	public Emprestimo_Livro() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String getObservacoesAtoEmprestimo() {
		return observacoesAtoEmprestimo;
	}

	public void setObservacoesAtoEmprestimo(String observacoesAtoEmprestimo) {
		this.observacoesAtoEmprestimo = observacoesAtoEmprestimo;
	}

}
