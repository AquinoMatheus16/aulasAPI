package br.org.serratec.projeto.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.projeto.dto.UsuarioDTO;
import br.org.serratec.projeto.dto.UsuarioInserirDTO;
import br.org.serratec.projeto.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
   @GetMapping
   public ResponseEntity<List<UsuarioDTO>> listar(){
	   return ResponseEntity.ok(usuarioService.lista());
   }

   @PostMapping
   public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {
       UsuarioDTO usuario = usuarioService.inserir(usuarioInserirDTO);
       URI uri = ServletUriComponentsBuilder
           .fromCurrentRequest()
           .path("/{id}")
           .buildAndExpand(usuario.getId())
           .toUri();
       return ResponseEntity.created(uri).body(usuario);
   }
	  

}
