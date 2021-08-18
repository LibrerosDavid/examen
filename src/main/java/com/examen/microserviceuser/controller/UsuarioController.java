package com.examen.microserviceuser.controller;

import com.examen.microserviceuser.model.UsuarioDTO;
import com.examen.microserviceuser.model.UsuarioEditDTO;
import com.examen.microserviceuser.service.inter.UsuarioService;
import com.examen.microserviceuser.util.Errores;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = {"*"})
@RestController
@Log4j2
public class UsuarioController {
    public Map<String, Object> response = new HashMap<>();
    private final UsuarioService usuarioService;

    @GetMapping("/usuario/todos")
    public ResponseEntity<?> getAllUser(@RequestParam Map<String, Object> requestParams) {
        log.info("GET: /usuario/todos ->" + requestParams.toString());

        try{
            Pageable pageable = null;
            Integer page = Integer.parseInt(requestParams.get("page").toString());
            Integer size = Integer.parseInt(requestParams.get("size").toString());
            pageable = PageRequest.of(page, size, Sort.by("id").descending());
            Page<UsuarioDTO> users =  usuarioService.findAllUser(pageable);
            response.put("data",users);
            response.put("error",null);
            return new ResponseEntity<Object>(response, HttpStatus.OK);

        }catch (Exception e ){
            log.error("GET: /usuario/todos ->" + e.getMessage());

            response.put("data",null);
            response.put("error",e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);

        }
    }

    @GetMapping("/usuario/email/{correo}")
    public ResponseEntity<?> getById(@PathVariable("correo") String email) {
        log.info("GET: /usuario/id/{id} ->" + email);

        try{
            UsuarioEditDTO usuarioEditDTO =  usuarioService.findByEmail(email);

            if (usuarioEditDTO!=null){

                response.put("data",usuarioEditDTO);
                response.put("error",null);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }else{
                response.put("message","usuario no encontrado");
                response.put("data",null);
                response.put("error",null);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }catch (Exception e ){
            log.error("GET: /usuario/id/{id} ->" + e.getMessage());

            response.put("data",null);
            response.put("error",e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/usuario/agrega")
    public ResponseEntity<?> addUser(@Valid @RequestBody UsuarioEditDTO usuarioEditDTO, BindingResult bindingResult) {
        log.info("GET: /usuario/agrega ->" + usuarioEditDTO.toString());

        if (bindingResult.hasErrors()) {
            response = Errores.listErrors(bindingResult, response);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            UsuarioEditDTO usuarioEditDTO1 = usuarioService.save(usuarioEditDTO);
            response.put("data",usuarioEditDTO1);
            response.put("error",null);
            return new ResponseEntity<Object>(response, HttpStatus.CREATED);
        }catch (Exception e){
            log.error("GET: /usuario/agrega ->" + e.getMessage());
            response.put("data",null);
            response.put("error",e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/usuario/actualiza/{email}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UsuarioEditDTO usuarioEditDTO, BindingResult bindingResult,@PathVariable("email") String email) {
        log.info("GET: /usuario/actualiza{email} ->" +" email:"+email+" usuario:"+usuarioEditDTO.toString());

        if (bindingResult.hasErrors()) {
            response = Errores.listErrors(bindingResult, response);
            response.put("data", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            UsuarioEditDTO usuarioEditDTO1 = usuarioService.update(usuarioEditDTO,email);

            if (usuarioEditDTO1!=null){
                response.put("data",usuarioEditDTO1);
                response.put("error",null);
                return new ResponseEntity<Object>(response, HttpStatus.CREATED);
            }else{
                response.put("message","usuario no encontrado");
                response.put("data",null);
                response.put("error",null);
                return new ResponseEntity<Object>(response, HttpStatus.CREATED);
            }

        }catch (Exception e){
            log.error("GET:  /usuario/actualiza{email} ->" + e.getMessage());
            response.put("data",null);
            response.put("error",e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.CONFLICT);
        }
    }

}
