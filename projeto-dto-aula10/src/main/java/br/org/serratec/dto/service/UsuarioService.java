package br.org.serratec.dto.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.config.MailConfig;
import br.org.serratec.dto.domain.Perfil;
import br.org.serratec.dto.domain.Usuario;
import br.org.serratec.dto.domain.UsuarioPerfil;
import br.org.serratec.dto.dto.UsuarioDTO;
import br.org.serratec.dto.dto.UsuarioInserirDTO;
import br.org.serratec.dto.exception.EmailException;
import br.org.serratec.dto.exception.SenhaException;
import br.org.serratec.dto.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	MailConfig mailConfig;

	public List<UsuarioDTO> lista() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		List<UsuarioDTO> usuariosDTO = new ArrayList<>();
		for (int i = 0; i < usuarios.size(); i++) {
			Usuario usuario = usuarios.get(i);
			usuariosDTO.add(new UsuarioDTO(usuario));
		}
		// return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
		return usuariosDTO;
	}

	@Autowired
	private PerfilService perfilService;

	public UsuarioDTO inserir(UsuarioInserirDTO user) throws EmailException {
		if (!user.getSenha().equalsIgnoreCase(user.getConfirmaSenha())) {
			throw new SenhaException("Senha e Confirma Senha não são iguais");
		}
		if (usuarioRepository.findByEmail(user.getEmail()) != null) {
			throw new EmailException("Email já existente");
		}
		Usuario usuario = new Usuario();
		usuario.setNome(user.getNome());
		usuario.setEmail(user.getEmail());
		usuario.setSenha(user.getSenha());

		Set<UsuarioPerfil> perfis = new HashSet<>();
		for (Perfil perfil : user.getPerfis()) {
			perfil = perfilService.buscar(perfil.getId());
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil(usuario, perfil, LocalDate.now());
			perfis.add(usuarioPerfil);
		}
		usuario.setUsuarioPerfis(perfis);
		usuario = usuarioRepository.save(usuario);
		mailConfig.sendEmail(usuario.getEmail(), "Cadastro de Usuario!", usuario.toString());
		return new UsuarioDTO(usuario);
	}

}