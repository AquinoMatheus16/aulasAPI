package br.org.serratec.aula8.teste;

import org.springframework.stereotype.Component;

@Component
public class Consulta {
	public Double calcularConsulta(Double valor) {
		return valor = valor + valor * 0.1;
	}
}
