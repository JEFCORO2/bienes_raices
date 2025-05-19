package com.jcoronel.backend.api.app.backend_bienes_raices.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsStringValidation implements ConstraintValidator<IsString, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // Asegura que contenga al menos una letra
        boolean contieneLetras = value.matches(".*[a-zA-ZáéíóúÁÉÍÓÚñÑ].*");

        // Asegura que solo haya letras, números y espacios
        boolean formatoValido = value.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\\s]+$");

        return contieneLetras || formatoValido;
    }
}
