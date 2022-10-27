package br.org.serratec.projeto.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public interface FuncionarioSalarioDTO {
	public Integer getIdade();

	public Double getMediaSalario();

	public Double getMenorSalario();

	public Double getMaiorSalario();

	public Double getTotalFuncionarios();
}
