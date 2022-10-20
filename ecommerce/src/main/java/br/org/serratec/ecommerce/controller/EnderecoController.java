package br.org.serratec.ecommerce.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.ecommerce.domain.Endereco;
import br.org.serratec.ecommerce.repository.EnderecoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRepository;

//	@Autowired
//	private EnderecoService enderecoService;

	@GetMapping
	@ApiOperation(value = "Lista todos os enderecos", notes = "Listagem de Enderecos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os enderecos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<List<Endereco>> buscar() {
		return ResponseEntity.ok(enderecoRepository.findAll());
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um endereco", notes = "Endereco")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um endereco"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Endereco> buscarPorId(@PathVariable Long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		if (!endereco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(endereco.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um endereco", notes = "Inserir Endereco")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Endereco adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Endereco> inserir(@Valid @RequestBody Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(endereco.getId())
				.toUri();
		return ResponseEntity.created(uri).body(endereco);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um endereco", notes = "Atualizar Endereco")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Endereco Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Endereco> atualizar(@Valid @RequestBody Endereco endereco, @PathVariable Long id) {
		Optional<Endereco> enderecoOptional = enderecoRepository.findById(id);
		if (!enderecoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Endereco enderecoBanco = enderecoOptional.get();
		enderecoBanco.setCep(endereco.getCep());
		enderecoBanco.setRua(endereco.getRua());
		enderecoBanco.setBairro(endereco.getBairro());
		enderecoBanco.setCidade(endereco.getCidade());
		enderecoBanco.setNumero(endereco.getNumero());
		enderecoBanco.setComplemento(endereco.getComplemento());
		enderecoBanco.setUf(endereco.getUf());
		endereco = enderecoRepository.save(enderecoBanco);
		return ResponseEntity.ok(endereco);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um endereco", notes = "Remover Endereco")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Endereco Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Optional<Endereco> enderecoBanco = enderecoRepository.findById(id);
		if (!enderecoBanco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		enderecoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
