package com.equipeRL.backend.Services.resourcesServices;

import com.equipeRL.backend.Models.Cidade;
import com.equipeRL.backend.Repositories.CidadesRepository;
import com.equipeRL.backend.Services.acervo.JornalService;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CidadeService implements ServiceInterface<Cidade> {

	private static Logger logger = Logger.getLogger(JornalService.class);

	@Autowired
	private CidadesRepository cidadesRepository;

    public List<Cidade> getAll() {

        List<Cidade> cidades = cidadesRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return cidades;

    }

    public boolean isExist(Cidade model) {

        Optional<Cidade> findCidade = cidadesRepository.findByNomeIgnoreCase(model.getNome());

        if (findCidade.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Cidade model) {

        cidadesRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Cidade findById(long id) {

        Optional<Cidade> findCidade = cidadesRepository.findById(id);

        if (findCidade.isPresent()) {
            logger.info("Item encontrado.");
            return findCidade.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Cidade model) {

        cidadesRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        cidadesRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
