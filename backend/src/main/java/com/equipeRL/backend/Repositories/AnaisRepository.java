package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.acervo.Anal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Anais, responsável por conter  métodos que tratam a Entidade Anal
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface AnaisRepository extends JpaRepository<Anal, Long> {
	
	public Optional<Anal> findByTituloIgnoreCase(String nome);

}
