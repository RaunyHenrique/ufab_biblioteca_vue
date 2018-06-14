package com.equipeRL.backend.Services.acervo;

import com.equipeRL.backend.Models.acervo.Revista;
import com.equipeRL.backend.Repositories.RevistasRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RevistaService implements ServiceInterface<Revista> {

	private static Logger logger = Logger.getLogger(RevistaService.class);

	@Autowired
	private RevistasRepository revistaRepository;

    public List<Revista> getAll() {

        List<Revista> revistas = revistaRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return revistas;

    }

    public boolean isExist(Revista model) {

        Optional<Revista> findRevista = revistaRepository.findByTituloIgnoreCase(model.getTitulo());

        if (findRevista.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Revista model) {

        revistaRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Revista findById(long id) {

        Optional<Revista> findRevista = revistaRepository.findById(id);

        if (findRevista.isPresent()) {
            logger.info("Item encontrado.");
            return findRevista.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Revista model) {

        revistaRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        revistaRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
