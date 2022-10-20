package br.org.serratec.dto.dto;

import java.util.Set;

import br.org.serratec.dto.domain.Perfil;

public class UsuarioInserirDTO {

	private String nome;
	private String email;
	private String senha;
	private String confirmaSenha;
	private Set<Perfil> perfis;

	public UsuarioInserirDTO(String nome, String email, String senha, String confirmaSenha, Set<Perfil> perfis) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.confirmaSenha = confirmaSenha;
		this.perfis = perfis;
	}

	public UsuarioInserirDTO() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}
//	Imagina que vc tem 5 pessoas morando na mesma casa, todas essas pessoas podem ser clientes da loja e todas tem o msm endereço, essa é a lógica que está ali, mas a gente está discutindo que talvez a melhor lógica seja que uma pessoa possa ter 1 ou muitos endereços, por exemplo, um cliente tem uma casa no rio, outra em sp, outra em minas, enfim.

}
