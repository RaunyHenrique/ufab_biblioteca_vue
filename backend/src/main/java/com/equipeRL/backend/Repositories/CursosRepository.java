package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Cursos, responsável por conter  métodos que tratam a Entidade Curso
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface CursosRepository extends JpaRepository<Curso, Long> {
	
	public Optional <Curso> findByNomeIgnoreCase(String nome);

}
