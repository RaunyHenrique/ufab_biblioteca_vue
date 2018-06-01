package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.acervo.Tcc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


/**
 * Esse é o repositório da classe Tccs, responsável por conter  métodos que tratam a Entidade Tcc
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface TccsRepository extends JpaRepository<Tcc, Long> {
	public Optional<Tcc> findByTituloIgnoreCase(String nome);
}
