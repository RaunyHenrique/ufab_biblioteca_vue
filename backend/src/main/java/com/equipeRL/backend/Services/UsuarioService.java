package com.equipeRL.backend.Services;

import com.equipeRL.backend.Models.Usuario;
import com.equipeRL.backend.Repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(Usuario user){
        user.setSenha(getPasswordEncoder().encode(user.getSenha()));
        repo.save(user);
    }

}
