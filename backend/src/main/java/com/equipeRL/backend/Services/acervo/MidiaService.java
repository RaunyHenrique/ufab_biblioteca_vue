package com.equipeRL.backend.Services.acervo;

import com.equipeRL.backend.Models.acervo.MidiasEletronicas;
import com.equipeRL.backend.Repositories.MidiasRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MidiaService implements ServiceInterface<MidiasEletronicas> {

	private static Logger logger = Logger.getLogger(MidiaService.class);

	@Autowired
	private MidiasRepository midiasRepository;

    public List<MidiasEletronicas> getAll() {

        List<MidiasEletronicas> midias = midiasRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return midias;

    }

    public boolean isExist(MidiasEletronicas model) {

        Optional<MidiasEletronicas> findMidia = midiasRepository.findByTituloIgnoreCase(model.getTitulo());

        if (findMidia.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(MidiasEletronicas model) {

        midiasRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public MidiasEletronicas findById(long id) {

        Optional<MidiasEletronicas> findMidia = midiasRepository.findById(id);

        if (findMidia.isPresent()) {
            logger.info("Item encontrado.");
            return findMidia.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(MidiasEletronicas model) {

        midiasRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        midiasRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
