package br.org.serratec.h2exercicio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Gerente extends Funcionario {

	@Column(length = 50)
	private String setor;

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

}
