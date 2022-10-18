package br.org.serratec.trabalho.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.trabalho.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
