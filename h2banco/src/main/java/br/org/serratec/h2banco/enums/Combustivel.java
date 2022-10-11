package br.org.serratec.h2banco.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.org.serratec.h2banco.exeption.EnumValidationException;

public enum Combustivel {
	ALCOOL(1, "Álcool"), GASOLINA(2, "Gasolina"), DIESEL(3, "Diesel"), FLEX(4, "Flex");

	private Integer codigo;
	private String tipo;

	private Combustivel(Integer codigo, String tipo) {
		this.codigo = codigo;
		this.tipo = tipo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getTipo() {
		return tipo;
	}

	@JsonCreator
	public static Combustivel verifica(Integer value) throws EnumValidationException {
		for (Combustivel c : values()) {
			if (value.equals(c.getCodigo())) {
				return c;
			}
		}
		throw new EnumValidationException("Categoria preenchida incorretamente");
	}
}
