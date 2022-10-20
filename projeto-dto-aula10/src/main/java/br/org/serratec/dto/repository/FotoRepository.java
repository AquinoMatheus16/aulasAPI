package br.org.serratec.dto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.dto.domain.Foto;
import br.org.serratec.dto.domain.Funcionario;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {
	public Optional<Foto> findByFuncionario(Funcionario funcionario);
}