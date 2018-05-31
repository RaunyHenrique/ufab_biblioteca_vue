package com.equipeRL.backend.Repositories;

import com.equipeRL.backend.Models.acervo.MidiasEletronicas;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Esse é o repositório da classe MidiasEletronicas, responsável por conter  métodos que tratam a Entidade MidiaEletronica
 * @author EquipeACL
 *
 */
@RepositoryRestResource
public interface MidiasRepository extends PagingAndSortingRepository<MidiasEletronicas, Long> {
	
	public Optional <MidiasEletronicas> findByTituloIgnoreCase(String titulo);

}
