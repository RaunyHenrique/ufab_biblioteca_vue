package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Editoras, responsável por conter  métodos que tratam a Entidade Editora
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface EditorasRepository extends JpaRepository<Editora, Long> {
	
	public Optional<Editora> findByNomeIgnoreCase(String nome);

}
