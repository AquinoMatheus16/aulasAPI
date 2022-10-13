package br.org.serratec.h2exercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.h2exercicio.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>{

}
