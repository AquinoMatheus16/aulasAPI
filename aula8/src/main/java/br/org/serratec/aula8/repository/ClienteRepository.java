package br.org.serratec.aula8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.aula8.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
