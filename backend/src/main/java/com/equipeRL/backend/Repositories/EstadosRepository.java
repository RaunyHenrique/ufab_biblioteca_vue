package com.equipeRL.backend.Repositories;


import com.equipeRL.backend.Models.Estado;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Esse é o repositório da classe Estados, responsável por conter  métodos que tratam a Entidade Estado
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface EstadosRepository extends PagingAndSortingRepository<Estado, Long> {
	
}
