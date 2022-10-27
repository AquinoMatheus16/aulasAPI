package br.org.serratec.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.projeto.domain.Endereco;
import br.org.serratec.projeto.dto.EnderecoDTO;
import br.org.serratec.projeto.dto.EnderecoViaCepDTO;
import br.org.serratec.projeto.repository.EnderecoRepository;

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

			Optional<EnderecoViaCepDTO> enderecoViaCep = Optional
					.ofNullable(restTemplate.getForObject(uri, EnderecoViaCepDTO.class));
			if (enderecoViaCep.get().getCep() != null) {
				String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
				enderecoViaCep.get().setCep(cepSemTraco);
				Endereco enderecoBanco = new Endereco();
				enderecoBanco.setCep(enderecoViaCep.get().getCep());
				enderecoBanco.setBairro(enderecoViaCep.get().getBairro());
				enderecoBanco.setComplemento(enderecoViaCep.get().getComplemento());
				enderecoBanco.setCidade(enderecoViaCep.get().getLocalidade());
				enderecoBanco.setLogradouro(enderecoViaCep.get().getLogradouro());
				enderecoBanco.setUf(enderecoViaCep.get().getUf());
				return inserir(enderecoBanco);
			} else {
				return null;
			}
		}
	}

	private EnderecoDTO inserir(Endereco endereco) {
		return new EnderecoDTO(enderecoRepository.save(endereco));
	}

}
