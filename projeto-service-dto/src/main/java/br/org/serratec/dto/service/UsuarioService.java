package br.org.serratec.dto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.domain.Usuario;
import br.org.serratec.dto.dto.UsuarioDTO;
import br.org.serratec.dto.dto.UsuarioInserirDTO;
import br.org.serratec.dto.exception.EmailException;
import br.org.serratec.dto.exception.SenhaException;
import br.org.serratec.dto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<UsuarioDTO> findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		for (Usuario usuario : usuarios) {
			usuariosDTO.add(new UsuarioDTO(usuario));
		}

//		A MESMA COISA DO DE CIMA
//		return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());

		return usuariosDTO;
	}

	public UsuarioDTO inserir(UsuarioInserirDTO user) {
		if (!user.getSenha().equalsIgnoreCase(user.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		if (usuarioRepository.findByEmail(user.getEmail()) != null) {
			throw new EmailException("Email já existente");
//			throw new EmailException(usuarioBanco.getNome() + " já está com esse e-mail " + usuarioBanco.getEmail());
		}
		Usuario usuario = new Usuario();
		usuario.setNome(user.getNome());
		usuario.setEmail(user.getEmail());
		usuario.setSenha(user.getSenha());
		return new UsuarioDTO(usuarioRepository.save(usuario));
	}

}