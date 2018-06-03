package com.equipeRL.backend.Services.resourcesServices;

import com.equipeRL.backend.Models.AreaConhecimento;
import com.equipeRL.backend.Repositories.AreasConhecimentoRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AreaConhecimentoService implements ServiceInterface<AreaConhecimento> {

    private static Logger logger = Logger.getLogger(AreaConhecimentoService.class);

    @Autowired
    private AreasConhecimentoRepository areasConhecimentoRepository;

    public List<AreaConhecimento> getAll() {

        List<AreaConhecimento> areasConhecimento = areasConhecimentoRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return areasConhecimento;

    }

    public boolean isExist(AreaConhecimento model) {

        Optional<AreaConhecimento> findAreaConhecimento = areasConhecimentoRepository.findByNomeIgnoreCase(model.getNome());

        if (findAreaConhecimento.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(AreaConhecimento model) {

        areasConhecimentoRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public AreaConhecimento findById(long id) {

        Optional<AreaConhecimento> findAreaConhecimento = areasConhecimentoRepository.findById(id);

        if (findAreaConhecimento.isPresent()) {
            logger.info("Item encontrado.");
            return findAreaConhecimento.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(AreaConhecimento model) {

        areasConhecimentoRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        areasConhecimentoRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
