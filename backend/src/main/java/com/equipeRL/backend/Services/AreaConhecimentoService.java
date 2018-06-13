package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.AreaConhecimento;
import com.equipeRL.backend.Repositories.AreasConhecimentoRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AreaConhecimentoService implements ServiceInterface<AreaConhecimento> {

	private static Logger logger = Logger.getLogger(AreaConhecimentoService.class);

	@Autowired
	private AreasConhecimentoRepository areaRepository;

    public List<AreaConhecimento> getAll() {

        List<AreaConhecimento> areas = areaRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return areas;

    }

    public boolean isExist(AreaConhecimento model) {

        Optional<AreaConhecimento> findArea = areaRepository.findByNomeIgnoreCase(model.getNome());

        if (findArea.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(AreaConhecimento model) {

        areaRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public AreaConhecimento findById(long id) {

        Optional<AreaConhecimento> findArea = areaRepository.findById(id);

        if (findArea.isPresent()) {
            logger.info("Item encontrado.");
            return findArea.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(AreaConhecimento model) {

        areaRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        areaRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
