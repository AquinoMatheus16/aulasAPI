package br.org.serratec.trabalho.controller;

//53d8e4ab-8d93-4d6f-a029-a699c0cc9821

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

//@RestController
//@RequestMapping("/livros")
//public class LivroController {
//	
//	@Autowired
//	private LivroRepository livroRepository;
//
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Livro inserir(@RequestBody Livro proprietario) {
//		return livroRepository.save(proprietario);
//	}
//
//	@PostMapping("/lista")
//	@ResponseStatus(HttpStatus.CREATED)
//	public List<Livro> inserirVarios(@RequestBody List<Livro> proprietarios) {
//		return livroRepository.saveAll(proprietarios);
//	}
//
//	@GetMapping
//	public ResponseEntity<List<Livro>> listar() {
//		List<Livro> proprietarios = livroRepository.findAll();
//		return ResponseEntity.ok(proprietarios);
//	}
//}
