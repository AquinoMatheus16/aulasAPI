package br.org.serratec.projeto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.projeto.domain.Foto;
import br.org.serratec.projeto.domain.Funcionario;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long>{
	Optional<Foto> findByFuncionario(Funcionario funcionario);
}
