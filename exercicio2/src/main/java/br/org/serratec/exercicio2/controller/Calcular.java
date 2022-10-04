package br.org.serratec.exercicio2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Calcular {

	@RequestMapping("/adicao")
	public Double adicao(@RequestParam Double n1,@RequestParam Double n2) {
		return n1 + n2;
	}

	@RequestMapping("/subtracao")
	public Double subtracao(@RequestParam Double n1, @RequestParam Double n2) {
		return n1 - n2;
	}

	@RequestMapping("/multiplicacao")
	public Double multiplicacao(@RequestParam Double n1, @RequestParam Double n2) {
		return n1 * n2;
	}

	@RequestMapping("/divisao")
	public Double divisao( @RequestParam Double n1,@RequestParam Double n2) {
		return n1 / n2;
	}

}
