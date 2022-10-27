package br.org.serratec.projeto.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FuncionarioDTO {
	private Long id;
	private String nome;
	private Double salario;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String url;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
