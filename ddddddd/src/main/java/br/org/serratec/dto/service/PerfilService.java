package br.org.serratec.dto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.domain.Perfil;
import br.org.serratec.dto.repository.PerfilRepository;

@Service
public class PerfilService {
	@Autowired
	private PerfilRepository perfilRepository;

	public Perfil buscar(Long id) {
		Optional<Perfil> perfil = perfilRepository.findById(id);
		return perfil.get();
	}
}
