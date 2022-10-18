package br.org.serratec.trabalho.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emprestimo_livro")
public class Emprestimo_Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_livro")
	private Livro livro;

	@Column(length = 200, nullable = false)
	private String observacoesAtoEmprestimo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
