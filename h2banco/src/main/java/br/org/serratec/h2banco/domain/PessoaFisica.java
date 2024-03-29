package br.org.serratec.h2banco.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PessoaFisica extends Fornecedor {
	@Column
	private String rg;
	@Column
	private String orgaoEmisso;
	@Column
	private String cpf;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoEmisso() {
		return orgaoEmisso;
	}

	public void setOrgaoEmisso(String orgaoEmisso) {
		this.orgaoEmisso = orgaoEmisso;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
