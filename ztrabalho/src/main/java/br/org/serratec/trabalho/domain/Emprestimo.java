package br.org.serratec.trabalho.domain;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_emprestimo")
	private Long id;

	@ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_associado")
    @Enumerated(EnumType.STRING)
	private Associado associado;

	@Column(nullable = false)
	private LocalDate dataEmprestimo;

	public Emprestimo(Long id, Associado associado, LocalDate dataEmprestimo) {
		super();
		this.id = id;
		this.associado = associado;
		this.dataEmprestimo = dataEmprestimo;
	}

	public Emprestimo() {
		super();
	}

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

}
