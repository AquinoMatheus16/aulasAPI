package br.org.serratec.dto.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.dto.UsuarioDTO;
import br.org.serratec.dto.dto.UsuarioInserirDTO;
import br.org.serratec.dto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

//	@GetMapping
//	public ResponseEntity<List<UsuarioDTO>> listar() {
//		return ResponseEntity.ok(usuarioService.lista());
//	}

//	@GetMapping
//	public ResponseEntity<List<UsuarioDTO>> listar() {
//		UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println("Login do usuario SecurityContextHolder: " + details.getUsername());
//		return ResponseEntity.ok(usuarioService.lista());
//	}

	@PostMapping
	public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuario) {
		UsuarioDTO usuarioDTO = usuarioService.inserir(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId())
				.toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
}