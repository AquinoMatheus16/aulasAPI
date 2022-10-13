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

import br.org.serratec.aula8.domain.Pedido;
import br.org.serratec.aula8.repository.PedidoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping
	@ApiOperation(value = "Lista todos os pedidos", notes = "Listagem de Pedidos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os pedidos"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public List<Pedido> listarPedido() {
		return pedidoRepository.findAll();
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um pedido", notes = "Pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um pedido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Pedido> pesquisarId(@PathVariable Long id) {
		Optional<Pedido> pedidoId = pedidoRepository.findById(id);
		if (pedidoId.isPresent()) {
			return ResponseEntity.ok(pedidoId.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Insere os dados de um Pedido", notes = "Inserir Pedido")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Pedido adcionado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public Pedido inserir(@Valid @RequestBody Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza dados de um pedido", notes = "Atualizar Pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido Atualizado"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Pedido> atualizar(@Valid @RequestBody Pedido pedido, @PathVariable Long id) {
		Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
		if (!pedidoOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Pedido pedidoBanco = pedidoOptional.get();
		pedidoBanco.setCliente(pedido.getCliente());
		pedidoBanco.setDataPedido(pedido.getDataPedido());
		pedidoBanco.setHoraPedido(pedido.getHoraPedido());
		pedidoBanco.setDataEntrega(pedido.getDataEntrega());
		pedido = pedidoRepository.save(pedidoBanco);
		return ResponseEntity.ok(pedido);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Remove um pedido", notes = "Remover Pedido")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Pedido Removido"),
			@ApiResponse(code = 401, message = "Erro de autenticação"),
			@ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
			@ApiResponse(code = 404, message = "Recurso não encontrado"),
			@ApiResponse(code = 505, message = "Exceção interna da aplicação"), })
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		if (!pedidoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		pedidoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
