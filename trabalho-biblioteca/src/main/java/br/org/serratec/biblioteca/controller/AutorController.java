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

import br.org.serratec.biblioteca.domain.Autor;
import br.org.serratec.biblioteca.dto.LivroPorAutorDTO;
import br.org.serratec.biblioteca.repository.AutorRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

	@Autowired
	private AutorRepository autorRepository;

	@GetMapping
	@ApiOperation(value = "Lista todos os autors", notes = "Listagem de Autors")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os autors"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public List<Autor> listarAutor() {
		return autorRepository.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um autors", notes = "Autor")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um autors"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Autor> pesquisarId(@PathVariable Long id) {
		Optional<Autor> autorId = autorRepository.findById(id);
		if (autorId.isPresent()) {
			return ResponseEntity.ok(autorId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um autor", notes = "Inserir Autor")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Autor adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public Autor inserir(@RequestBody Autor autors) {
		return autorRepository.save(autors);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um autor", notes = "Atualizar Autor")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Autor Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Autor> atualizar(@Valid @RequestBody Autor autor, @PathVariable Long id) {
		Optional<Autor> autorOptional = autorRepository.findById(id);
		if (!autorOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Autor autorBanco = autorOptional.get();
		autorBanco.setNome(autor.getNome());
		autor = autorRepository.save(autorBanco);
		return ResponseEntity.ok(autor);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um autor", notes = "Remover Autor")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Autor Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!autorRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		autorRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/listar-total-livros")
	@ApiOperation(value = "Listar total de livros por autor", notes = "Listar Livros")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Livros listado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<List<LivroPorAutorDTO>> buscaLivroQtdAutor() {
		return ResponseEntity.ok(autorRepository.buscaLivroPorAutor());
	}
}
