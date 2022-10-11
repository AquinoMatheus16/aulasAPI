package br.org.serratec.aula6.controller;

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

import br.org.serratec.aula6.domain.Cliente;
import br.org.serratec.aula6.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listarCliente() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> pesquisarId(@PathVariable Long id) {
		Optional<Cliente> clienteId = clienteRepository.findById(id);
		if (clienteId.isPresent()) {
			return ResponseEntity.ok(clienteId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		if (!clienteOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Cliente clienteBanco = clienteOptional.get();
		clienteBanco.setNome(cliente.getNome());
		clienteBanco.setCpf(cliente.getCpf());
		clienteBanco.setEmail(cliente.getEmail());
		clienteBanco.setDataNascimento(cliente.getDataNascimento());
		clienteBanco.setEndereco(cliente.getEndereco());
		cliente = clienteRepository.save(clienteBanco);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
