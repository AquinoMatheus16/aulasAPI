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

import br.org.serratec.biblioteca.domain.Emprestimo;
import br.org.serratec.biblioteca.repository.EmprestimoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/emprestimo")
public class EmprestimoController {

	@Autowired
	private EmprestimoRepository emprestimoRepository;

	@GetMapping
	@ApiOperation(value = "Lista todos os emprestimos", notes = "Listagem de Emprestimos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os emprestimos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public List<Emprestimo> listarEmprestimo() {
		return emprestimoRepository.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um emprestimos", notes = "Emprestimo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um emprestimos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Emprestimo> pesquisarId(@PathVariable Long id) {
		Optional<Emprestimo> emprestimoId = emprestimoRepository.findById(id);
		if (emprestimoId.isPresent()) {
			return ResponseEntity.ok(emprestimoId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um emprestimo", notes = "Inserir Emprestimo")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Emprestimo adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public Emprestimo inserir(@RequestBody Emprestimo emprestimos) {
		return emprestimoRepository.save(emprestimos);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um emprestimo", notes = "Atualizar Emprestimo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Emprestimo Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Emprestimo> atualizar(@Valid @RequestBody Emprestimo emprestimo, @PathVariable Long id) {
		Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(id);
		if (!emprestimoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Emprestimo emprestimoBanco = emprestimoOptional.get();
		emprestimoBanco.setAssociado(emprestimo.getAssociado());
		emprestimoBanco.setDataEmprestimo(emprestimo.getDataEmprestimo());
		emprestimoBanco.setEmprestimoLivro(emprestimo.getEmprestimoLivro());
		emprestimo = emprestimoRepository.save(emprestimoBanco);
		return ResponseEntity.ok(emprestimo);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um emprestimo", notes = "Remover Emprestimo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Emprestimo Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!emprestimoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		emprestimoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
