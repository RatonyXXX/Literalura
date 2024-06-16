package com.alurachallenge.literalura.common;

import com.alurachallenge.literalura.model.Datos;
import com.alurachallenge.literalura.model.DatosAutores;

public class Validar {

    public static boolean validarDatos(Datos datos) {

        if (datos == null || datos.resultados() == null || datos.resultados().isEmpty()) {
            System.out.println("No se encontraron datos.\n");
            return false;
        }

        // Tomar el primer resultado
        var primerResultado = datos.resultados().get(0);

        // Validar campos críticos del resultado
        if (primerResultado.titulo() == null || primerResultado.titulo().isEmpty()) {
            System.out.println("El título del libro no puede estar vacio.");
            return false;
        }

        // Validar campo idiomas
//        if (primerResultado.idiomas() == null || primerResultado.idiomas().isEmpty()) {
//            System.out.println("Debe especificarse al menos un idioma para el libro.");
//            return false;
//        }

        // Validar campo autor
        if (primerResultado.autores() == null || primerResultado.autores().isEmpty()) {
            System.out.println("Debe especificarse al menos un autor para el libro.");
            return false;
        }

        // Tomar el primer autor
        DatosAutores primerAutor = primerResultado.autores().get(0);

        // Validar autor
        if (primerAutor.nombre() == null || primerAutor.nombre().isEmpty()) {
            System.out.println("El nombre autor no contiene datos.");
            return false;
        }

        // Validar campo fecha nacimiento
//        if (primerAutor.fechaDeNacimiento() == null || primerAutor.fechaDeNacimiento().isEmpty()) {
//            System.out.println("La fecha de nacimiento del autor no contiene datos.");
//            return false;
//        }

        // Validar campo fecha nacimiento
//        if (primerAutor.fechaDeFallecimiento() == null || primerAutor.fechaDeFallecimiento().isEmpty()) {
//            System.out.println("La fecha de fallecimiento del autor no contiene datos.");
//            return false;
//        }

        return true;
    }
}