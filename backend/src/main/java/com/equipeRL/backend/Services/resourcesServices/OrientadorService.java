package com.equipeRL.backend.Services.resourcesServices;

import com.equipeRL.backend.Models.Orientador;
import com.equipeRL.backend.Repositories.OrientadoresRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrientadorService implements ServiceInterface<Orientador> {

	private static Logger logger = Logger.getLogger(OrientadorService.class);

	@Autowired
	private OrientadoresRepository orientadoresRepository;

    public List<Orientador> getAll() {

        List<Orientador> orientadores = orientadoresRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return orientadores;

    }

    public boolean isExist(Orientador model) {

        Optional<Orientador> findOrientador = orientadoresRepository.findByNomeIgnoreCase(model.getNome());

        if (findOrientador.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Orientador model) {

        orientadoresRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Orientador findById(long id) {

        Optional<Orientador> findOrientador = orientadoresRepository.findById(id);

        if (findOrientador.isPresent()) {
            logger.info("Item encontrado.");
            return findOrientador.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Orientador model) {

        orientadoresRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        orientadoresRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
