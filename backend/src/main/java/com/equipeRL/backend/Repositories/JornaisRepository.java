package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.acervo.Jornal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe Jornais, responsável por conter  métodos que tratam a Entidade Jornal
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface JornaisRepository extends JpaRepository<Jornal, Long> {
	public Optional <Jornal> findByTituloIgnoreCase(String titulo);

}
