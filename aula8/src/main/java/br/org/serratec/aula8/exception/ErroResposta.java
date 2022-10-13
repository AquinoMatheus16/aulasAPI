package br.org.serratec.aula8.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ErroResposta {

	private Integer status;
	private String titulo;
	private LocalDateTime dataHora;
	private List<String> erros;

	public ErroResposta(Integer status, String titulo, LocalDateTime dataHora, List<String> erros) {
		super();
		this.status = status;
		this.titulo = titulo;
		this.dataHora = dataHora;
		this.erros = erros;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

}
