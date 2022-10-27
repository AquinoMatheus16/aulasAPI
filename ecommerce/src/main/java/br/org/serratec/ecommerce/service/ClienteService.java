package br.org.serratec.ecommerce.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.ecommerce.domain.Cliente;
import br.org.serratec.ecommerce.dto.ClienteDTO;
import br.org.serratec.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public ClienteDTO alterarDto(Cliente funcionario) {
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setNomeCompleto(clienteDto.getNomeCompleto());
		clienteDto.setCpf(clienteDto.getCpf());
		clienteDto.setTelefone(clienteDto.getTelefone());
		clienteDto.setDataNascimento(clienteDto.getDataNascimento());
		clienteDto.setCep(clienteDto.getCep());
		clienteDto.setNumero(clienteDto.getNumero());
		return clienteDto;
	}

	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}
	
	@Transactional
    public void deleteById(Long id) {
		clienteRepository.deleteById(id);
    }

	public ClienteDTO inserir(Cliente cliente, MultipartFile file) throws IOException {
		cliente = clienteRepository.save(cliente);
		return alterarDto(cliente);
	}

	public Cliente save(@Valid Cliente cliente) {
		return clienteRepository.save(cliente);
	}
}
