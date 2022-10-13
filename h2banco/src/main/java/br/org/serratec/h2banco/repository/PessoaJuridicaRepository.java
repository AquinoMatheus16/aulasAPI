package br.org.serratec.h2banco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.h2banco.domain.Veiculo;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<Veiculo,Long>{

}
