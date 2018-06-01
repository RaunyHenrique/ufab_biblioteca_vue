package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Temas, responsável por conter  métodos que tratam a Entidade Tema
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface TemasRepository extends JpaRepository<Tema, Long> {
	
	public Optional<Tema> findByNomeIgnoreCase(String nome);

}
