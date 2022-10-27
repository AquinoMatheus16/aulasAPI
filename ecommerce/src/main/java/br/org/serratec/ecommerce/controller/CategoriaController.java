package br.org.serratec.ecommerce.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.ecommerce.domain.Categoria;
import br.org.serratec.ecommerce.service.CategoriaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> buscar() {
		return ResponseEntity.ok(categoriaService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaService.findById(id);
		if (!categoria.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(categoria.get());
	}

	@PostMapping
	public ResponseEntity<Categoria> inserir(@Valid @RequestBody Categoria categoria) {
		categoria = categoriaService.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).body(categoria);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		Optional<Categoria> categoriaBanco = categoriaService.findById(id);
		if (!categoriaBanco.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		categoriaService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
