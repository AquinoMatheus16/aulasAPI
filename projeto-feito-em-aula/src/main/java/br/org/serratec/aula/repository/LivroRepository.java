package br.org.serratec.aula.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.aula.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

}
