package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Grupo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Grupos, responsável por conter  métodos que tratam a Entidade Grupo
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface GruposRepository extends PagingAndSortingRepository<Grupo, Long> {
	
	Optional <Grupo> findByNomeIgnoreCase(String nome);

}
