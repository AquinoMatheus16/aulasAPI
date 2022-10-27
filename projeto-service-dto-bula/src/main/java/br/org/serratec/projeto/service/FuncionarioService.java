package br.org.serratec.projeto.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.projeto.domain.Funcionario;
import br.org.serratec.projeto.dto.FuncionarioDTO;
import br.org.serratec.projeto.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Autowired
	FotoService fotoService;
	
	public List<FuncionarioDTO> listar(){
		Object  detail =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (detail instanceof UserDetails) {
			UserDetails userDetail = (UserDetails) detail;
			System.out.println("Usuario: " + userDetail.getUsername() + " - " + userDetail.getPassword());
		} else {
			System.out.println("Nao veio userDetails = " + detail);
		}

		List<Funcionario> funcionarios = funcionarioRepository.findAll();
		List<FuncionarioDTO> dtos = new ArrayList<>();
		for(Funcionario funcionario : funcionarios) {
			FuncionarioDTO dto = adicionarImagemUri(funcionario);
			dtos.add(dto);
		}
		return dtos;
	}
	
	
	public FuncionarioDTO adicionarImagemUri(Funcionario funcionario) {
	       FuncionarioDTO dto = new FuncionarioDTO();
	       dto.setId(funcionario.getId());
	       dto.setNome(funcionario.getNome());
	       dto.setDataNascimento(funcionario.getDataNascimento());
	       dto.setSalario(funcionario.getSalario());
			URI uri = ServletUriComponentsBuilder
			           .fromCurrentContextPath()
			           .path("/funcionarios/{id}/foto")
			           .buildAndExpand(funcionario.getId())
			           .toUri();
	       
	       
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
