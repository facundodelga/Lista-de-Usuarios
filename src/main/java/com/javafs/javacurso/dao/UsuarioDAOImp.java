package com.javafs.javacurso.dao;

import com.javafs.javacurso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Transactional
public class UsuarioDAOImp implements UsuarioDAO{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";

        List<Usuario> resultado = entityManager.createQuery(query).getResultList();

        return resultado;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class,id);

        entityManager.remove(usuario);

    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    @Override
    public boolean verificarLogin(Usuario usuario) {
        boolean op;

        String query = "FROM Usuario WHERE email = :email";
        try {
            List<Usuario> resultado = entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();

            String passHashed = resultado.get(0).getPassword();

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

            op = argon2.verify(passHashed,usuario.getPassword());
        }
        catch (NullPointerException e){
            return false;
        }

        return op;
    }

    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        boolean op;

        String query = "FROM Usuario WHERE email = :email";

        List<Usuario> resultado = entityManager.createQuery(query).setParameter("email", usuario.getEmail()).getResultList();

        if(resultado.isEmpty()){
            return null;
        }

        String passHashed = resultado.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

        if( argon2.verify(passHashed,usuario.getPassword()) ){
            return resultado.get(0);
        }

        return null;
    }


}
