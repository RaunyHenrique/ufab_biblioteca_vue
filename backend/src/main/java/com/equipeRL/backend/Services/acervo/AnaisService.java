package com.equipeRL.backend.Services.acervo;

import com.equipeRL.backend.Models.acervo.Anal;
import com.equipeRL.backend.Repositories.AnaisRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AnaisService implements ServiceInterface<Anal> {

	private static Logger logger = Logger.getLogger(AnaisService.class);

	@Autowired
	private AnaisRepository anaisRepository;

    public List<Anal> getAll() {

        List<Anal> anais = anaisRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return anais;

    }

    public boolean isExist(Anal model) {

        Optional<Anal> findAnal = anaisRepository.findByTituloIgnoreCase(model.getTitulo());

        if (findAnal.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Anal model) {

        anaisRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Anal findById(long id) {

        Optional<Anal> findAnal = anaisRepository.findById(id);

        if (findAnal.isPresent()) {
            logger.info("Item encontrado.");
            return findAnal.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Anal model) {

        anaisRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        anaisRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
