package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Aluno;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Alunos, responsável por conter  métodos que tratam a Entidade Aluno
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface AlunosRepository extends PagingAndSortingRepository<Aluno,Long> {
	
	public Optional <Aluno> findByNomeIgnoreCase(String nome);
	public Optional <Aluno> findByMatriculaIgnoreCase(String matricula);
	public Optional <Aluno> findByCpfIgnoreCase(String cpf);
}
