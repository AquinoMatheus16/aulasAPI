package br.org.serratec.aula6.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.aula6.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
