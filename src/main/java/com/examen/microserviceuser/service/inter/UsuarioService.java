package com.examen.microserviceuser.service.inter;

import com.examen.microserviceuser.entity.Usuario;
import com.examen.microserviceuser.model.UsuarioDTO;
import com.examen.microserviceuser.model.UsuarioEditDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    Page<UsuarioDTO> findAllUser(Pageable pageable);
    UsuarioEditDTO findByEmail(String email);
    UsuarioEditDTO findById(Long ig);
    UsuarioEditDTO save(UsuarioEditDTO usuarioEditDTO);
    UsuarioEditDTO update(UsuarioEditDTO usuarioEditDTO,String email);

    boolean deleteByEmail(String email);
}
