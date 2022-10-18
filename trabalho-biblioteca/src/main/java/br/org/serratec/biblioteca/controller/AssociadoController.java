package br.org.serratec.biblioteca.controller;

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

import br.org.serratec.biblioteca.domain.Associado;
import br.org.serratec.biblioteca.repository.AssociadoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/associado")
public class AssociadoController {

	@Autowired
	private AssociadoRepository associadoRepository;

	@GetMapping
	@ApiOperation(value = "Lista todos os associados", notes = "Listagem de Associados")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os associados"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public List<Associado> listarAssociado() {
		return associadoRepository.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um associados", notes = "Associado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um associados"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Associado> pesquisarId(@PathVariable Long id) {
		Optional<Associado> associadoId = associadoRepository.findById(id);
		if (associadoId.isPresent()) {
			return ResponseEntity.ok(associadoId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um associado", notes = "Inserir Associado")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Associado adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public Associado inserir(@RequestBody Associado associados) {
		return associadoRepository.save(associados);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um associado", notes = "Atualizar Associado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Associado Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Associado> atualizar(@Valid @RequestBody Associado associado, @PathVariable Long id) {
		Optional<Associado> associadoOptional = associadoRepository.findById(id);
		if (!associadoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Associado associadoBanco = associadoOptional.get();
		associadoBanco.setNome(associado.getNome());
		associadoBanco.setTelefone(associado.getTelefone());
		associadoBanco.setCpf(associado.getCpf());
		associadoBanco.setLogradouro(associado.getLogradouro());
		associadoBanco.setNumero(associado.getNumero());
		associadoBanco.setComplemento(associado.getComplemento());
		associadoBanco.setBairro(associado.getBairro());
		associadoBanco.setCidade(associado.getCidade());
		associadoBanco.setEstado(associado.getEstado());
		associado = associadoRepository.save(associadoBanco);
		return ResponseEntity.ok(associado);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um associado", notes = "Remover Associado")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Associado Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!associadoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		associadoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
