package br.org.serratec.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.projeto.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	public Endereco findByCep(String cep);
}
