package br.org.serratec.projeto.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.projeto.domain.Foto;
import br.org.serratec.projeto.domain.Funcionario;
import br.org.serratec.projeto.repository.FotoRepository;

@Service
public class FotoService {
	@Autowired
	FotoRepository fotoRepository;
	
	public Foto inserir(Funcionario funcionario, MultipartFile file ) throws IOException {
		Foto foto = new Foto();
		foto.setFuncionario(funcionario);
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setDados(file.getBytes());
		foto = fotoRepository.save(foto);
		return foto;
	}
	
	public Foto buscaPorIdFuncionario(Long id) {
		Funcionario funcionario = new Funcionario();
		funcionario.setId(id);
		Optional<Foto> foto = fotoRepository.findByFuncionario(funcionario);
		if (foto.isEmpty()) {
			return null;
		}
		return foto.get();
	}
	
}
