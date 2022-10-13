package br.org.serratec.aula8.controller;

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

import br.org.serratec.aula8.domain.Cliente;
import br.org.serratec.aula8.repository.ClienteRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	@ApiOperation(value = "Lista todos os cliente", notes = "Listagem de Clientes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os clientes"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public List<Cliente> listarCliente() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um cliente", notes = "Cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um cliente"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Cliente> pesquisarId(@PathVariable Long id) {
		Optional<Cliente> clienteId = clienteRepository.findById(id);
		if (clienteId.isPresent()) {
			return ResponseEntity.ok(clienteId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um Cliente", notes = "Inserir Cliente")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public Cliente inserir(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um cliente", notes = "Atualizar Cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
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
	@ApiOperation(value = "Remove um cliente", notes = "Remover Cliente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cliente Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
