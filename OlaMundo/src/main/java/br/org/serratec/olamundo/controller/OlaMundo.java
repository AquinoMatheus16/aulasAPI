package br.org.serratec.olamundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OlaMundo {

	@RequestMapping("/ola")
	public String olaMundo() {
		return "Olá Mundo!";
	}

	@RequestMapping("/resposta")
	public String resposta() {
		return "Lagoa";
	}

	@GetMapping("/maiuscula")
	public String maiuscula(@RequestParam String texto, @RequestParam String nome) {
		return texto.toUpperCase() + " - " + nome;
	}

//	@GetMapping("/maiuscula")
//	public String maiuscula(@RequestParam String texto, @RequestParam Integer idade) {
//		return texto.toUpperCase() + " - " + idade.toString();
//	}

	@GetMapping("/tamanho")
	public Integer tamanho(@RequestParam String texto) {
		return texto.length();
	}
}
