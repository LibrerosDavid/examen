package com.examen.microserviceuser.repository;

import com.examen.microserviceuser.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario,Long> {

    Usuario findByCorreo(String correo);
    void deleteByCorreo(String correo);
}
