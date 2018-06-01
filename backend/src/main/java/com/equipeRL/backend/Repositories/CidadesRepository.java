package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Cidades, responsável por conter  métodos que tratam a Entidade Cidade
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface CidadesRepository extends JpaRepository<Cidade, Long> {
	
	public Optional <Cidade> findByNomeIgnoreCase(String nome);
	

}
