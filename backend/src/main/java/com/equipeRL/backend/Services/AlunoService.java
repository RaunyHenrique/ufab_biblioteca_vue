package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.Aluno;
import com.equipeRL.backend.Repositories.AlunosRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AlunoService implements ServiceInterface<Aluno> {

	private static Logger logger = Logger.getLogger(AlunoService.class);

	@Autowired
	private AlunosRepository alunosRepository;

    public List<Aluno> getAll() {

        List<Aluno> alunos = alunosRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return alunos;

    }

    public boolean isExist(Aluno model) {

        Optional<Aluno> findAluno = alunosRepository.findByNomeIgnoreCase(model.getNome());

        if (findAluno.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Aluno model) {

        alunosRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Aluno findById(long id) {

        Optional<Aluno> findAluno = alunosRepository.findById(id);

        if (findAluno.isPresent()) {
            logger.info("Item encontrado.");
            return findAluno.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Aluno model) {

        alunosRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        alunosRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
