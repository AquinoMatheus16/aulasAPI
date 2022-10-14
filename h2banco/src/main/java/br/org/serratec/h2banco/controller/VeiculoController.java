package br.org.serratec.h2banco.controller;

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

import br.org.serratec.h2banco.domain.Veiculo;
import br.org.serratec.h2banco.repository.VeiculoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	@Autowired
	private VeiculoRepository veiculoRepository;

	@GetMapping
	@ApiOperation(value = "Lista todos os veiculos", notes = "Listagem de Veiculos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os veiculos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public List<Veiculo> listar() {
		return veiculoRepository.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um Veiculo", notes = "Veiculo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um veiculo"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Veiculo> buscar(@PathVariable Long id) {
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
		if (!veiculo.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(veiculo.get());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um Veiculo", notes = "Inserir Veiculo")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Veiculo adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public Veiculo inserir(@Valid @RequestBody Veiculo veiculo) {
		return veiculoRepository.save(veiculo);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um veiculo", notes = "Atualizar Veiculo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Veiculo Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Veiculo> alterar(@PathVariable Long id, @Valid @RequestBody Veiculo veiculo) {

		if (!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		veiculo.setId(id);
		return ResponseEntity.ok(veiculoRepository.save(veiculo));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um veiculo", notes = "Remover Veiculo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Veiculo Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> apagar(@PathVariable Long id) {
		if (!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		veiculoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}