package br.org.serratec.aula.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.aula.domain.Categoria;
import br.org.serratec.aula.exception.DataNotFoundException;
import br.org.serratec.aula.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> listarTodos() {
		List<Categoria> categorias = categoriaService.findAll();
		return ResponseEntity.ok(categorias);
	}

	@PostMapping
	public ResponseEntity<Categoria> incluir(@Valid @RequestBody Categoria categoria) {
		categoria = categoriaService.incluir(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(categoria.getIdCategoria()).toUri();
		return ResponseEntity.created(uri).body(categoria);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscaCategoria(@PathVariable Long id) throws DataNotFoundException {
		Categoria categoria = categoriaService.buscaPorId(id);
		return ResponseEntity.ok(categoria);
	}
	// sci hub, genesis library, 12ft ladder
}
//CREATE TABLE categoria (
//id_categoria serial PRIMARY KEY, 
//nome varchar(30)NOT NULL, 
//descricao varchar(200));
//
//CREATE TABLE livro (
//id_livro serial PRIMARY KEY, 
//titulo varchar (40) NOT null,
//id_categoria int,
//FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria),
//isbn varchar(20) NOT NULL UNIQUE, 
//data_publicacao date);