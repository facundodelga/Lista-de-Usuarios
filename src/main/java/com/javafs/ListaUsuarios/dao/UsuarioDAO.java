package com.javafs.ListaUsuarios.dao;

import com.javafs.ListaUsuarios.models.Usuario;


import java.util.List;

public interface UsuarioDAO{
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registrar(Usuario usuario);

    boolean verificarLogin(Usuario usuario);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
