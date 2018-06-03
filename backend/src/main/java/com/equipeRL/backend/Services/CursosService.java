package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.Curso;
import com.equipeRL.backend.Repositories.CursosRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursosService implements ServiceInterface<Curso> {

    private static Logger logger = Logger.getLogger(CursosService.class);

    @Autowired
    private CursosRepository cursosRepository;

    public List<Curso> getAll() {

        List<Curso> cursos = cursosRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return cursos;

    }

    public boolean isExist(Curso model) {

        Optional<Curso> findCurso = cursosRepository.findByNomeIgnoreCase(model.getNome());

        if (findCurso.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Curso model) {

        cursosRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Curso findById(long id) {

        Optional<Curso> findCurso = cursosRepository.findById(id);

        if (findCurso.isPresent()) {
            logger.info("Item encontrado.");
            return findCurso.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Curso model) {

        cursosRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        cursosRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }

}
