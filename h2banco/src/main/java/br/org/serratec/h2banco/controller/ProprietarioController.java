package br.org.serratec.h2banco.controller;

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

import br.org.serratec.h2banco.domain.Proprietario;
import br.org.serratec.h2banco.repository.ProprientarioRepository;

@RestController
@RequestMapping("/proprientario")
public class ProprietarioController {
	@Autowired
	private ProprientarioRepository veiculoRepository;

	@GetMapping
	public List<Proprietario> listar() {
		return veiculoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Proprietario> buscar(@PathVariable Long id) {
		Optional<Proprietario> veiculo = veiculoRepository.findById(id);
		if (!veiculo.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculo.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Proprietario inserir(@Valid @RequestBody Proprietario veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Proprietario> alterar(@PathVariable Long id, @Valid @RequestBody Proprietario veiculo) {

		if (!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		veiculo.setId(id);
		return ResponseEntity.ok(veiculoRepository.save(veiculo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		veiculoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
