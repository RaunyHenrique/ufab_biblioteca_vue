package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.acervo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


/**
 * Esse é o repositório da classe Livros, responsável por conter  métodos que tratam a Entidade Livro
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface LivrosRepository extends JpaRepository<Livro, Long> {
	
	public Optional <Livro> findByTituloIgnoreCase(String nome);
}
