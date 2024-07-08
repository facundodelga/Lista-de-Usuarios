package com.javafs.ListaUsuarios.controllers;

import com.javafs.ListaUsuarios.dao.UsuarioDAO;
import com.javafs.ListaUsuarios.models.Usuario;
import com.javafs.ListaUsuarios.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDAO usuarioDAO;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validarUsuario(String token){
        String usuarioId = jwtUtil.getKey(token);

        if(usuarioId != null){
            return true;
        }

        return false;
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token){
        if(!validarUsuario(token))
            return null;

        return usuarioDAO.getUsuarios();
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminarUsuario(@RequestHeader(value = "Authorization") String token,
                                @PathVariable Long id){

        if(!validarUsuario(token))
            return;

        usuarioDAO.eliminar(id);
    }

    @RequestMapping(value = "api/registrar", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String contra = argon2.hash(1, 1024, 1, usuario.getPassword());

        usuario.setPassword(contra);

        usuarioDAO.registrar(usuario);
    }


    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){ //busca la id dado por el ruteo
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Robert");
        usuario.setApellido("Mefisto");
        usuario.setEmail("robert.mefisto@gmail.com");
        usuario.setTelefono("32382382381");
        return usuario;
    }


}
