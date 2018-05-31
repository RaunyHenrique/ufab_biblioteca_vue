package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Autor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Autores, responsável por conter  métodos que tratam a Entidade Autor
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface AutoresRepository extends PagingAndSortingRepository<Autor, Long> {

	public Optional <Autor> findByNomeIgnoreCase(String nome);
}
