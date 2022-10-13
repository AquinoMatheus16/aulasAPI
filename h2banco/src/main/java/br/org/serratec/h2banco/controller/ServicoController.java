package br.org.serratec.h2banco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.h2banco.domain.Servico;
import br.org.serratec.h2banco.repository.ServicoRepository;

@RestController
@RequestMapping("/servicos")
public class ServicoController {
	@Autowired
	private ServicoRepository servicoRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Servico inserir(@RequestBody Servico servico) {
		return servicoRepository.save(servico);
	}

	@GetMapping
	public ResponseEntity<List<Servico>> listar() {
		List<Servico> manutencoes = servicoRepository.findAll();
		return ResponseEntity.ok(manutencoes);
	}

	@GetMapping("{id}")
	public ResponseEntity<Servico> buscar(@PathVariable Long id) {
		Optional<Servico> servico = servicoRepository.findById(id);
		if (servico.isPresent()) {
			return ResponseEntity.ok(servico.get());
		}
		return ResponseEntity.notFound().build();
	}
}
