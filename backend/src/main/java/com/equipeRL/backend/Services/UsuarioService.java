package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.Usuario;
import com.equipeRL.backend.Repositories.UsuarioRepository;
import com.equipeRL.backend.Services.interfaces.ServiceInterface;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements ServiceInterface<Usuario> {

    private static Logger logger = Logger.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> getAll() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        logger.info("Itens listados com sucesso!");

        return usuarios;

    }

    public boolean isExist(Usuario model) {

        Optional<Usuario> findUsuario = usuarioRepository.findByNomeIgnoreCase(model.getNome());

        if (findUsuario.isPresent()) {

            logger.error("Item já existe no banco de dados.");
            return true;

        }

        logger.info("Item ainda não cadastrado no banco de dados.");
        return false;

    }

    public void save(Usuario model) {

        //encripta a senha do usuário
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        usuarioRepository.save(model);
        logger.info("Item salvo com sucesso.");

    }

    public Usuario findById(long id) {

        Optional<Usuario> findUsuario = usuarioRepository.findById(id);

        if (findUsuario.isPresent()) {
            logger.info("Item encontrado.");
            return findUsuario.get();
        }

        logger.warn("Item não encontrado.");
        return null;

    }

    public void update(Usuario model) {

        //OBS: Não alterar a senha... enviar recuperação de senha
        usuarioRepository.save(model);
        logger.info("Item atualizado com sucesso.");

    }

    public void deleteById(long id) {

        usuarioRepository.deleteById(id);
        logger.info("Item deletado com sucesso.");

    }
}
