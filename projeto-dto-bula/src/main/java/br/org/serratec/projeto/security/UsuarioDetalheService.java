package br.org.serratec.projeto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.org.serratec.projeto.domain.Usuario;
import br.org.serratec.projeto.repository.UsuarioRepository;

@Service
public class UsuarioDetalheService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username);
		if (usuario == null) {
			throw new RuntimeException();
		}
		return new UsuarioDetalhe(usuario);
	}

}
