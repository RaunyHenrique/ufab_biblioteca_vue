package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Funcionarios, responsável por conter  métodos que tratam a Entidade Funcionario
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	public Optional<Funcionario> findByNomeIgnoreCase(String nome);
	public Optional<Funcionario> findByLoginIgnoreCase(String login);

}

