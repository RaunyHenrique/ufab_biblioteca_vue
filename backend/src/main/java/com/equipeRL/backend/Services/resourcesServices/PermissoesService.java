package com.equipeRL.backend.Services.resourcesServices;

import com.equipeRL.backend.Models.Permissao;
import com.equipeRL.backend.Repositories.PermissoesRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissoesService implements ServiceInterface<Permissao> {

    private static Logger logger = Logger.getLogger(PermissoesService.class);

    @Autowired
    private PermissoesRepository permissoesRepository;

    public List<Permissao> getAll() {

        List<Permissao> permissoes = permissoesRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return permissoes;

    }

    public boolean isExist(Permissao model) {

        Optional<Permissao> permissao = permissoesRepository.findByNomeIgnoreCase(model.getNome());

        if (permissao.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Permissao model) {

        permissoesRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Permissao findById(long id) {

        Optional<Permissao> permissao = permissoesRepository.findById(id);

        if (permissao.isPresent()) {
            logger.info("Item encontrado.");
            return permissao.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Permissao model) {

        permissoesRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        permissoesRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
