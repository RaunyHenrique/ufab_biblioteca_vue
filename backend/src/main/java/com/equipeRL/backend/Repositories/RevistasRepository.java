package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.acervo.Revista;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


/**
 * Esse é o repositório da classe Revistas, responsável por conter  métodos que tratam a Entidade Revista
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface RevistasRepository extends PagingAndSortingRepository<Revista, Long> {
	public Optional<Revista> findByTituloIgnoreCase(String nome);
}
