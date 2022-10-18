package br.org.serratec.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.org.serratec.biblioteca.domain.Autor;
import br.org.serratec.biblioteca.dto.LivroPorAutorDTO;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

	// listar total de livros por autor
	@Query(value = "select a.nome , count(a.nome) from autor a inner join livro_autor la on a.id = la.id_livro group by a.nome;", nativeQuery = true)
	List<LivroPorAutorDTO> buscaLivroPorAutor();
}
