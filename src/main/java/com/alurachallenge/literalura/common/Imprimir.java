package com.alurachallenge.literalura.common;

import com.alurachallenge.literalura.model.Autores;
import com.alurachallenge.literalura.model.Libros;

import java.util.List;

public class Imprimir {

    public static void imprimirLibro(Libros libro, List<Autores> autor) {
        System.out.println();
        System.out.println("----- LIBRO -----");
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.print("Autores: ");
        autor.forEach(a -> System.out.println(a.getNombre()));
        System.out.println("Idiomas: " + libro.getIdiomas());
        System.out.println("Numero de Descargas: " + libro.getNumeroDeDescargas());
        System.out.println("-----------------");
    }

    public static void imprimirAutores(Autores autores, List<Libros> libros) {
        System.out.println();
        System.out.println("Autor: " + autores.getNombre());
        System.out.println("Fecha de Nacimiento: " + autores.getFechaDeNacimiento());
        System.out.println("Fecha de Fallecimiento: " + autores.getFechaDeFallecimiento());
        System.out.println("Libros: ");
        libros.forEach(libro -> System.out.println(" - " + libro.getTitulo()));
    }

}