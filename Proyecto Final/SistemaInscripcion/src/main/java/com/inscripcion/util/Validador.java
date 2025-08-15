package com.inscripcion.util;

import java.util.regex.Pattern;

/**
 * Clase utilitaria para validar datos de entrada.
 */
public class Validador {
    
    // Patrón para validar correos electrónicos
    private static final Pattern PATRON_CORREO = Pattern.compile(
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );
    
    /**
     * Valida que un texto no sea nulo ni esté vacío.
     * 
     * @param texto Texto a validar
     * @return true si el texto es válido, false en caso contrario
     */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
    
    /**
     * Valida que un número sea positivo.
     * 
     * @param numero Número a validar
     * @return true si el número es positivo, false en caso contrario
     */
    public static boolean esNumeroPositivo(Integer numero) {
        return numero != null && numero > 0;
    }
    
    /**
     * Valida que un correo electrónico tenga un formato válido.
     * 
     * @param correo Correo a validar
     * @return true si el correo tiene un formato válido, false en caso contrario
     */
    public static boolean esCorreoValido(String correo) {
        if (correo == null) {
            return false;
        }
        return PATRON_CORREO.matcher(correo).matches();
    }
    
    /**
     * Valida que un teléfono tenga un formato válido.
     * 
     * @param telefono Teléfono a validar
     * @return true si el teléfono tiene un formato válido, false en caso contrario
     */
    public static boolean esTelefonoValido(String telefono) {
        if (telefono == null) {
            return true; // El teléfono es opcional
        }
        // Eliminar espacios, guiones y paréntesis para validar solo dígitos
        String telefonoLimpio = telefono.replaceAll("[\\s\\-()]", "");
        return telefonoLimpio.matches("\\d{7,15}"); // Entre 7 y 15 dígitos
    }
}