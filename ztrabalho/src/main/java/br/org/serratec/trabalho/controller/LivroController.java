package br.org.serratec.trabalho.controller;

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

import br.org.serratec.trabalho.domain.Livro;
import br.org.serratec.trabalho.repository.LivroRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroRepository livroRepository;

	@GetMapping
	@ApiOperation(value = "Lista todos os livros", notes = "Listagem de Livros")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os livros"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public List<Livro> listarLivro() {
		return livroRepository.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um livros", notes = "Livro")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um livros"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Livro> pesquisarId(@PathVariable Long id) {
		Optional<Livro> livroId = livroRepository.findById(id);
		if (livroId.isPresent()) {
			return ResponseEntity.ok(livroId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um livro", notes = "Inserir Livro")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Livro adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public Livro inserir(@RequestBody Livro livros) {
		return livroRepository.save(livros);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um livro", notes = "Atualizar Livro")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Livro Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Livro> atualizar(@Valid @RequestBody Livro livro, @PathVariable Long id) {
		Optional<Livro> livroOptional = livroRepository.findById(id);
		if (!livroOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Livro livroBanco = livroOptional.get();
		livroBanco.setCategoria(livro.getCategoria());
		livroBanco.setTitulo(livro.getTitulo());
		livroBanco.setDataPublicacao(livro.getDataPublicacao());
		livroBanco.setIsbn(livro.getIsbn());
		livro = livroRepository.save(livroBanco);
		return ResponseEntity.ok(livro);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um livro", notes = "Remover Livro")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Livro Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!livroRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		livroRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
