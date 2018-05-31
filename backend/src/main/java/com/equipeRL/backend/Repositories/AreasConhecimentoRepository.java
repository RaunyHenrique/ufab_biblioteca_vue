package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.AreaConhecimento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe AreasConhecimento, responsável por conter  métodos que tratam a Entidade AreaConhecimento
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface AreasConhecimentoRepository extends PagingAndSortingRepository<AreaConhecimento, Long> {

	public Optional <AreaConhecimento> findByNomeIgnoreCase(String nome);

}
