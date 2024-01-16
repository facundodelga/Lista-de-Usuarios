package com.javafs.javacurso.dao;

import com.javafs.javacurso.models.Usuario;


import java.util.List;

public interface UsuarioDAO{
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    boolean verificarLogin(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
