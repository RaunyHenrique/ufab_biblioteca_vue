package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Permissoes, responsável por conter  métodos que tratam a Entidade Permissao
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface PermissoesRepository extends JpaRepository<Permissao, Long> {
	
	Optional <Permissao> findByNomeIgnoreCase(String nome);

}
