package br.org.serratec.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.trabalho.domain.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long>{

}
