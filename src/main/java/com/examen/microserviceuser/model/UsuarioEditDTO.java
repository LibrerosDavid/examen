package com.examen.microserviceuser.model;

import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UsuarioEditDTO {
    @NotEmpty(message = "nombre requerido")
    private String nombre;
    @NotEmpty(message = "apellido paterno requerido")
    private String apellidoPaterno;
    @NotEmpty(message = "apellido materno requerido")
    private String apellidoMaterno;
    @NotEmpty(message = "cedula requerido")
    private String cedula;
    @Email(message = "tiene que ser un correo valido")
    @NotEmpty(message = "no puede estar vacio el correo")
    private String correo;
    @NotEmpty(message = "telefono requerido")
    private String telefono;
}
