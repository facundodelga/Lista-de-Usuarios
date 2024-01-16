package com.javafs.javacurso.controllers;

import com.javafs.javacurso.dao.UsuarioDAO;
import com.javafs.javacurso.models.Usuario;
import com.javafs.javacurso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDAO usuarioDAO;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario){

        Usuario usuariolog = usuarioDAO.obtenerUsuarioPorCredenciales(usuario);
        if(usuariolog != null) {
            String token = jwtUtil.create(String.valueOf(usuariolog.getId()), usuario.getEmail());

            return token;
        }

        return "FAIL";

    }
}
