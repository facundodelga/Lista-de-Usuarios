package com.javafs.ListaUsuarios.config;

import com.javafs.ListaUsuarios.dao.UsuarioDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public interface UsuarioConfig{
    @Bean
    default CommandLineRunner commandLineRunner(UsuarioDAO usuarioDAO){
        return args -> {
            usuarioDAO.getUsuarios();

        };
    }
}
