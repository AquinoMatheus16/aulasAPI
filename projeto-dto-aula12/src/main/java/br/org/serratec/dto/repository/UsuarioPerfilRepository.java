package br.org.serratec.dto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.dto.domain.UsuarioPerfil;
import br.org.serratec.dto.domain.UsuarioPerfilPK;

@Repository
public interface UsuarioPerfilRepository extends JpaRepository<UsuarioPerfil, UsuarioPerfilPK> {

}
