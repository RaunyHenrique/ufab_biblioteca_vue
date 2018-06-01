package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Orientador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Orientadores, responsável por conter  métodos que tratam a Entidade Orientador
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface OrientadoresRepository extends JpaRepository<Orientador, Long> {
	
	Optional <Orientador> findByNomeIgnoreCase(String nome);

}
