package br.org.serratec.h2banco.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.org.serratec.h2banco.enums.Categoria;
import br.org.serratec.h2banco.enums.Combustivel;

@Embeddable
public class Caracteristica {
	private String renavam;
	private String chassi;
	private Long ano;

	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	@Enumerated(EnumType.ORDINAL)
	private Combustivel combustivel;

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Combustivel getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(Combustivel combustivel) {
		this.combustivel = combustivel;
	}

}
