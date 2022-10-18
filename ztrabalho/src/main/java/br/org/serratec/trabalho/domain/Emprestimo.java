package br.org.serratec.trabalho.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_associado", nullable = false)
	private Associado associado;

	@Column(name = "data_emprestimo", nullable = false)
	private LocalDate dataEmprestimo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_emprestimo")
	private List<Emprestimo_Livro> emprestimoLivro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Associado getAssociado() {
		return associado;
	}

	public void setAssociado(Associado associado) {
		this.associado = associado;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public List<Emprestimo_Livro> getEmprestimoLivro() {
		return emprestimoLivro;
	}

	public void setEmprestimoLivro(List<Emprestimo_Livro> emprestimoLivro) {
		this.emprestimoLivro = emprestimoLivro;
	}

}
