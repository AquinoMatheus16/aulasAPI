package br.org.serratec.h2banco.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.org.serratec.h2banco.exeption.EnumValidationException;

public enum Categoria {
	HATCH, SEDAN, PICAPE, SUV, CONVERSIVEL, MINIVAN;

	@JsonCreator
	public static Categoria verifica(String value) throws EnumValidationException {
		for (Categoria categ : values()) {
			if (value.equals(categ.name())) {
				return categ;
			}
		}
		throw new EnumValidationException("Categoria " + value + " não existe.");
	}
}
