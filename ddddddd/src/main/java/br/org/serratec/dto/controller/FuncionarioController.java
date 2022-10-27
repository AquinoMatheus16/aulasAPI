package br.org.serratec.dto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.dto.domain.Funcionario;
import br.org.serratec.dto.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public ResponseEntity<List<Funcionario>> listar() {
		List<Funcionario> funcionarios =

				funcionarioRepository.findAll();
		return ResponseEntity.ok(funcionarios);
	}
}
