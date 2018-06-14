package com.equipeRL.backend.Services.acervo;

import com.equipeRL.backend.Models.acervo.Tcc;
import com.equipeRL.backend.Repositories.TccsRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TccService implements ServiceInterface<Tcc> {

	private static Logger logger = Logger.getLogger(TccService.class);

	@Autowired
	private TccsRepository tccsRepository;

    public List<Tcc> getAll() {

        List<Tcc> tccs = tccsRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return tccs;

    }

    public boolean isExist(Tcc model) {

        Optional<Tcc> findTcc = tccsRepository.findByTituloIgnoreCase(model.getTitulo());

        if (findTcc.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Tcc model) {

        tccsRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Tcc findById(long id) {

        Optional<Tcc> findTcc= tccsRepository.findById(id);

        if (findTcc.isPresent()) {
            logger.info("Item encontrado.");
            return findTcc.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Tcc model) {

        tccsRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        tccsRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
