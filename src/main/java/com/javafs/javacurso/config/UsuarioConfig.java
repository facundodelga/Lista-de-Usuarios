package com.javafs.javacurso.config;

import com.javafs.javacurso.dao.UsuarioDAO;
import com.javafs.javacurso.dao.UsuarioDAOImp;
import com.javafs.javacurso.models.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

@Configuration
public interface UsuarioConfig{
    @Bean
    default CommandLineRunner commandLineRunner(UsuarioDAO usuarioDAO){
        return args -> {
            usuarioDAO.getUsuarios();

        };
    }
}
