package br.org.serratec.aula8.teste;

import org.springframework.stereotype.Component;

@Component
public class Pagamento {
	private Consulta consulta;
	private Exame exame;

	public Pagamento(Consulta consulta, Exame exame) {
		this.consulta = consulta;
		this.exame = exame;
	}

	public Double calcularProcedimento(Double valorConsulta, Double valorExame) {
		return consulta.calcularConsulta(valorConsulta) + exame.calcularExame(valorExame);

	}

}
