package br.org.serratec.ecommerce.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.ecommerce.domain.Cliente;
import br.org.serratec.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<Cliente>> buscar() {
		return ResponseEntity.ok(clienteService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		Optional<Cliente> emprestimo = clienteService.findById(id);
		if (!emprestimo.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(emprestimo.get());
	}

	@PostMapping
	public ResponseEntity<Cliente> inserir(@Valid @RequestBody Cliente cliente) {
		cliente = clienteService.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();
		return ResponseEntity.created(uri).body(cliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Optional<Cliente> clienteBanco = clienteService.findById(id);
		if (!clienteBanco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		clienteService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
