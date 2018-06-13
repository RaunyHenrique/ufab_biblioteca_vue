package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.acervo.Livro;
import com.equipeRL.backend.Repositories.LivrosRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LivroService implements ServiceInterface<Livro> {

	private static Logger logger = Logger.getLogger(LivroService.class);

	@Autowired
	private LivrosRepository livroRepository;

    public List<Livro> getAll() {

        List<Livro> livros = livroRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return livros;

    }

    public boolean isExist(Livro model) {

        Optional<Livro> findLivro = livroRepository.findByTituloIgnoreCase(model.getTitulo());

        if (findLivro.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Livro model) {

        livroRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Livro findById(long id) {

        Optional<Livro> findLivro = livroRepository.findById(id);

        if (findLivro.isPresent()) {
            logger.info("Item encontrado.");
            return findLivro.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Livro model) {

        livroRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        livroRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
