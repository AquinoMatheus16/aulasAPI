package br.org.serratec.dto.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.domain.Funcionario;
import br.org.serratec.dto.dto.FuncionarioDTO;
import br.org.serratec.dto.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private FotoService fotoService;

	public List<FuncionarioDTO> listar() {
		List<FuncionarioDTO> funcionarioDTOs = funcionarioRepository.findAll().stream().map(f -> adicionarImagemUri(f))
				.collect(Collectors.toList());
		return funcionarioDTOs;
	}

	public FuncionarioDTO adicionarImagemUri(Funcionario funcionario) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionarios/{id}/foto")
				.buildAndExpand(funcionario.getId()).toUri();
		FuncionarioDTO dto = new FuncionarioDTO();
		dto.setNome(funcionario.getNome());
		dto.setDataNascimento(funcionario.getDataNascimento());
		dto.setSalario(funcionario.getSalario());
		dto.setUrl(uri.toString());
		return dto;
	}

	public FuncionarioDTO buscar(Long id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
		return adicionarImagemUri(funcionario.get());
	}

	public FuncionarioDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		funcionario = funcionarioRepository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return adicionarImagemUri(funcionario);
	}

}