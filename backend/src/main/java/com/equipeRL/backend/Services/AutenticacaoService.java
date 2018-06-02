package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.Usuario;
import com.equipeRL.backend.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(email);

        System.out.println("OPS: " + usuario.getEmail());

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado!");
        }

        return usuario;

    }
}
