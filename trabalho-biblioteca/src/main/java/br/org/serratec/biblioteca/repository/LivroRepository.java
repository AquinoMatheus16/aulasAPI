package br.org.serratec.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.serratec.biblioteca.domain.Livro;
import br.org.serratec.biblioteca.dto.LivroCategoriaDTO;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	// liste apenas o nome do livro e da categoria, ordenado por categoria
	@Query(value = "select l.titulo, c.nome from livro l, categoria c order by c.nome asc;", nativeQuery = true)
	List<LivroCategoriaDTO> buscaTituloCategoria();

	// listar total de livros por autor
}