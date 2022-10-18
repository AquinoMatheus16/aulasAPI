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

import br.org.serratec.trabalho.domain.Categoria;
import br.org.serratec.trabalho.repository.CategoriaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	@ApiOperation(value = "Lista todos os categorias", notes = "Listagem de Categorias")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os categorias"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public List<Categoria> listarCategoria() {
		return categoriaRepository.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um categorias", notes = "Categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um categorias"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Categoria> pesquisarId(@PathVariable Long id) {
		Optional<Categoria> categoriaId = categoriaRepository.findById(id);
		if (categoriaId.isPresent()) {
			return ResponseEntity.ok(categoriaId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um categoria", notes = "Inserir Categoria")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Categoria adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public Categoria inserir(@RequestBody Categoria categorias) {
		return categoriaRepository.save(categorias);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um categoria", notes = "Atualizar Categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Categoria> atualizar(@Valid @RequestBody Categoria categoria, @PathVariable Long id) {
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
		if (!categoriaOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Categoria categoriaBanco = categoriaOptional.get();
		
		
		
		categoria = categoriaRepository.save(categoriaBanco);
		return ResponseEntity.ok(categoria);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um categoria", notes = "Remover Categoria")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Categoria Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!categoriaRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		categoriaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
