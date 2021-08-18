package com.examen.microserviceuser.util;

import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Errores {
    static ModelMapper modelMapper = new ModelMapper();

    public static Map<String, Object> listErrors(BindingResult result, Map<String, Object> response) {
        List<String> errors = result.getFieldErrors()
                .stream()
                .map(error -> "El campo '" + error.getField() + "' " + error.getDefaultMessage())
                .collect(Collectors.toList());
        response.put("error", errors);
        return response;
    }
}
