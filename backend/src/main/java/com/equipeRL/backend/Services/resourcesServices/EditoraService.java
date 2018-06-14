package com.equipeRL.backend.Services.resourcesServices;

import com.equipeRL.backend.Models.Editora;
import com.equipeRL.backend.Repositories.EditorasRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EditoraService implements ServiceInterface<Editora> {

	private static Logger logger = Logger.getLogger(EditoraService.class);

	@Autowired
	private EditorasRepository editoraRepository;

    public List<Editora> getAll() {

        List<Editora> editoras = editoraRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return editoras;

    }

    public boolean isExist(Editora model) {

        Optional<Editora> findEditora = editoraRepository.findByNomeIgnoreCase(model.getNome());

        if (findEditora.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Editora model) {

        editoraRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Editora findById(long id) {

        Optional<Editora> findEditora = editoraRepository.findById(id);

        if (findEditora.isPresent()) {
            logger.info("Item encontrado.");
            return findEditora.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Editora model) {

        editoraRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        editoraRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
