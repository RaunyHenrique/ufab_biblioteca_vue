package com.equipeRL.backend.Services.resourcesServices;

import com.equipeRL.backend.Models.Tema;
import com.equipeRL.backend.Repositories.TemasRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TemaService implements ServiceInterface<Tema> {

	private static Logger logger = Logger.getLogger(TemaService.class);

	@Autowired
	private TemasRepository temasRepository;

    public List<Tema> getAll() {

        List<Tema> temas = temasRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return temas;

    }

    public boolean isExist(Tema model) {

        Optional<Tema> findTema = temasRepository.findByNomeIgnoreCase(model.getNome());

        if (findTema.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Tema model) {

        temasRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Tema findById(long id) {

        Optional<Tema> findTema = temasRepository.findById(id);

        if (findTema.isPresent()) {
            logger.info("Item encontrado.");
            return findTema.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Tema model) {

        temasRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        temasRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
