package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Aluno;
import com.equipeRL.backend.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNomeIgnoreCase(String nome);
    public Usuario findByUsername(String username);

}
