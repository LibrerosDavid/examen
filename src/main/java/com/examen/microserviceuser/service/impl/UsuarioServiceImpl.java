package com.examen.microserviceuser.service.impl;

import com.examen.microserviceuser.configuration.ModelMapperConfig;
import com.examen.microserviceuser.entity.Usuario;
import com.examen.microserviceuser.model.UsuarioDTO;
import com.examen.microserviceuser.model.UsuarioEditDTO;
import com.examen.microserviceuser.repository.UsuariosRepository;
import com.examen.microserviceuser.service.inter.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Log4j2
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuariosRepository usuariosRepository;
    private final ModelMapperConfig modelMapper;


    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAllUser(Pageable pageable) {
        return usuariosRepository.findAll(pageable).map(usuario -> modelMapper.modelMapper().map(usuario,UsuarioDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioEditDTO findByEmail(String email) {
        UsuarioEditDTO usuarioEditDTO = null;
        Usuario usuario = usuariosRepository.findByCorreo(email);
        if (usuario!=null){
            usuarioEditDTO = modelMapper.modelMapper().map(usuario,UsuarioEditDTO.class);
        }
        return usuarioEditDTO;
    }

    @Override
    public UsuarioEditDTO findById(Long ig) {
        Usuario usuario = usuariosRepository.findById(ig).orElse(null);
        UsuarioEditDTO usuarioEditDTO = null;
        if (usuario!=null){
            usuarioEditDTO =  modelMapper.modelMapper().map(usuario,UsuarioEditDTO.class);
        }
        return usuarioEditDTO;
    }

    @Override
    @Transactional
    public UsuarioEditDTO save(UsuarioEditDTO usuarioEditDTO) {
        Usuario usuario= modelMapper.modelMapper().map(usuarioEditDTO,Usuario.class);
        Usuario usuario1 = usuariosRepository.save(usuario);
        return modelMapper.modelMapper().map(usuario1,UsuarioEditDTO.class);

    }

    @Override
    @Transactional
    public UsuarioEditDTO update(UsuarioEditDTO usuarioEditDTO,String email) {
        Usuario usuario = usuariosRepository.findByCorreo(email);
        if (usuario==null){
            return null;
        }
        Usuario save = modelMapper.modelMapper().map(usuarioEditDTO,Usuario.class);
        save.setId(usuario.getId());
        Usuario usuario1 = usuariosRepository.save(save);
        return modelMapper.modelMapper().map(usuario1,UsuarioEditDTO.class);
    }

    @Override
    public boolean deleteByEmail(String email) {
        return false;
    }
}
