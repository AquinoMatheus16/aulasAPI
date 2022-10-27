package br.org.serratec.dto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.dto.domain.Endereco;
import br.org.serratec.dto.dto.EnderecoDTO;
import br.org.serratec.dto.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	private EnderecoRepository enderecoRepository;

	public EnderecoDTO buscar(String cep) {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		} else {
			RestTemplate restTemplate = new RestTemplate();
			String uri = "http://viacep.com.br/ws/" + cep + "/json";
//			Endereco enderecoViaCept = restTemplate.getForObject(uri, Endereco.class);
//			Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uri, Endereco.class));
			Optional<Endereco> enderecoViaCep = Optional.ofNullable(restTemplate.getForObject(uri, Endereco.class));

			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				return inserir(enderecoViaCep.get());
			} else {
				return null;
			}
		}
	}

	private EnderecoDTO inserir(Endereco endereco) {
		return new EnderecoDTO(enderecoRepository.save(endereco));
	}
}
