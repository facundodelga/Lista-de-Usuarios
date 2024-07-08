package com.javafs.ListaUsuarios.controllers;

import com.javafs.ListaUsuarios.dao.UsuarioDAO;
import com.javafs.ListaUsuarios.models.Usuario;
import com.javafs.ListaUsuarios.utils.JWTUtil;
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
