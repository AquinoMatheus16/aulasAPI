package br.org.serratec.aula8.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.aula8.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
