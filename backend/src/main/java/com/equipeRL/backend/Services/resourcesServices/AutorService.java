package com.equipeRL.backend.Services.resourcesServices;

import com.equipeRL.backend.Models.Autor;
import com.equipeRL.backend.Repositories.AutoresRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AutorService implements ServiceInterface<Autor> {

	private static Logger logger = Logger.getLogger(AutorService.class);

	@Autowired
	private AutoresRepository autorRepository;

    public List<Autor> getAll() {

        List<Autor> autores = autorRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return autores;

    }

    public boolean isExist(Autor model) {

        Optional<Autor> findAutor = autorRepository.findByNomeIgnoreCase(model.getNome());

        if (findAutor.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Autor model) {

        autorRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Autor findById(long id) {

        Optional<Autor> findAutor = autorRepository.findById(id);

        if (findAutor.isPresent()) {
            logger.info("Item encontrado.");
            return findAutor.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Autor model) {

        autorRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        autorRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
