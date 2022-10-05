package br.org.serratec.exercicio2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculos")
public class Calculadora {

	@GetMapping("/adicao")
	public Double adicao(@RequestParam Double n1, @RequestParam Double n2) {
		return n1 + n2;
	}

	@GetMapping("/subtracao")
	public Double subtracao(@RequestParam Double n1, @RequestParam Double n2) {
		return n1 - n2;
	}

	@GetMapping("/multiplicacao")
	public Double multiplicacao(@RequestParam Double n1, @RequestParam Double n2) {
		return n1 * n2;
	}

	@GetMapping("/divisao")
	public Double divisao(@RequestParam Double n1, @RequestParam Double n2) {
		return n1 / n2;
	}

//	@GetMapping("/adicaoPath/{n1}/{n2}")
//	public Double adicao(@PathVariable Double n1, @PathVariable Double n2) {
//		return n1 + n2;
//	}
	
}
