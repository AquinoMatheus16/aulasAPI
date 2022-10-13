package br.org.serratec.h2exercicio.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.h2exercicio.domain.Funcionario;
import br.org.serratec.h2exercicio.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public List<Funcionario> listarCliente() {
		return funcionarioRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> pesquisarId(@PathVariable Long id) {
		Optional<Funcionario> funcionarioId = funcionarioRepository.findById(id);
		if (funcionarioId.isPresent()) {
			return ResponseEntity.ok(funcionarioId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario inserir(@Valid @RequestBody Funcionario funcionario) {
		return funcionarioRepository.save(funcionario);
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<Funcionario> atualizar(@Valid @RequestBody Funcionario funcionario, @PathVariable Long id) {
//		Optional<Funcionario> funcionarioOptional = clienteRepository.findById(id);
//		if (!funcionarioOptional.isPresent()) {
//			return ResponseEntity.notFound().build();
//		}
//		Funcionario clienteBanco = funcionarioOptional.get();
//		clienteBanco.setNome(funcionario.getNome());
//		clienteBanco.setCpf(funcionario.getCpf());
//		clienteBanco.setSalario(funcionario.getSalario());
//		clienteBanco.setTurno(funcionario.getTurno());
//		funcionario = funcionarioRepository.save(clienteBanco);
//		return ResponseEntity.ok(funcionario);
//	}

	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> alterar(@PathVariable Long id, @Valid @RequestBody Funcionario funcionario) {

		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionario.setId(id);
		return ResponseEntity.ok(funcionarioRepository.save(funcionario));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!funcionarioRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		funcionarioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
