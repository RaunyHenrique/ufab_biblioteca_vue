package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.acervo.Jornal;
import com.equipeRL.backend.Repositories.JornaisRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JornalService implements ServiceInterface<Jornal> {

	private static Logger logger = Logger.getLogger(JornalService.class);

	@Autowired
	private JornaisRepository jornaisRepository;

    public List<Jornal> getAll() {

        List<Jornal> jornais = jornaisRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return jornais;

    }

    public boolean isExist(Jornal model) {

        Optional<Jornal> findJornal = jornaisRepository.findByTituloIgnoreCase(model.getTitulo());

        if (findJornal.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Jornal model) {

        jornaisRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Jornal findById(long id) {

        Optional<Jornal> findJornal = jornaisRepository.findById(id);

        if (findJornal.isPresent()) {
            logger.info("Item encontrado.");
            return findJornal.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Jornal model) {

        jornaisRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        jornaisRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
