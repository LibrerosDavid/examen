package com.examen.microserviceuser.model;

import lombok.Data;

@Data
public class UsuarioDTO {


    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;

}
