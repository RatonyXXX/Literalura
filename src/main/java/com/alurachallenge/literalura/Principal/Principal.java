package com.alurachallenge.literalura.Principal;

import com.alurachallenge.literalura.common.Imprimir;

import com.alurachallenge.literalura.common.Validar;
import com.alurachallenge.literalura.model.Datos;
import com.alurachallenge.literalura.model.DatosLibros;
import com.alurachallenge.literalura.model.DatosAutores;
import com.alurachallenge.literalura.model.Libros;
import com.alurachallenge.literalura.model.Autores;
import com.alurachallenge.literalura.repository.AutoresRepository;
import com.alurachallenge.literalura.repository.LibrosRepository;
import com.alurachallenge.literalura.service.ConsumoAPI;
import com.alurachallenge.literalura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private static final String URL = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos convertir = new ConvierteDatos();

    private LibrosRepository librosRepositorio;
    private AutoresRepository autoresRepositorio;
    private List<Libros> libros;
    private List<Autores> autores;

    public Principal(LibrosRepository librosRepository, AutoresRepository autoresRepository) {
        this.librosRepositorio = librosRepository;
        this.autoresRepositorio = autoresRepository;
    }

    public void mostrarMenu() {
        boolean salir = false;

        while (!salir) {
            var menu = """
                    
                    -------------- CHALLENGE LITERALURA --------------
                    1 - Buscar libro por titulo (WEB) 
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    """;
            System.out.println(menu);

            try {
                String input = teclado.nextLine();
                int opcion = Integer.parseInt(input);

                switch (opcion) {
                    case 1:
                        buscarLibroWeb();
                        break;
                    case 2:
                        listarLibros();
                        break;
                    case 3:
                        listarAutores();
                        break;
                    case 4:
                        listarAutoresAnio();
                        break;
                    case 5:
                        listarLibrosIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...\n");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.\n");
                }
            } catch(NumberFormatException e){
                System.out.println("Entrada no válida. Por favor, ingrese un número.\n");
            }
        }

        teclado.close(); // Cerrar el Scanner al finalizar
        System.exit(0);
    }

    private void buscarLibroWeb() {
        System.out.println("Buscar libro por titulo:");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL + "?search=" + tituloLibro.replace(" ", "+"));
        var datos = convertir.obtenerDatos(json, Datos.class);

        // Validar datos de libros y autores
        if (Validar.validarDatos(datos)) {
            datos.resultados()
                    .stream()
                    .findFirst()
                    .ifPresent(resultado -> {
                        // Crear instancia de Libros
                        Libros libro = new Libros(new DatosLibros(
                                resultado.titulo(),
                                resultado.autores(),
                                resultado.idiomas(),
                                resultado.numeroDeDescargas()
                        ));

                        // Datos del primer autor
                        DatosAutores datosAutor = resultado.autores().get(0);

                        try {
                            // Obtiene el autor de la BD si existe
                            Autores autor = autoresRepositorio.findByNombre(datosAutor.nombre())
                                    .orElse(new Autores(datosAutor));

                            // Agregar libro a lista de libros del autor
                            autor.getLibros().add(libro);

                            // Guardar autor y libro en la BD
                            libro.setAutor(autor);
                            autoresRepositorio.save(autor);

                            Imprimir.imprimirLibro(libro, List.of(libro.getAutor()));

                        } catch (DataIntegrityViolationException e) {

                            System.out.println("\nEl libro ya existe en la base de datos.");
                            System.out.println(libro + "\n");
                        }
                    });
        }
    }

    private void listarLibros() {
        libros = librosRepositorio.findAll();

        libros.sort(Comparator.comparing(Libros::getTitulo, String.CASE_INSENSITIVE_ORDER));

        opcionImprimirLibros(libros);

    }

    private void listarAutores() {
        autores = autoresRepositorio.findAll();

        autores.sort(Comparator.comparing(Autores::getNombre, String.CASE_INSENSITIVE_ORDER));

        opcionImprimirAutores(autores);
    }

    private void listarAutoresAnio() {
        System.out.println("Ingrese el año de autor(es) que desea buscars: ");
        int anio = Integer.parseInt(teclado.nextLine());

        List<Autores> autoresVivos = autoresRepositorio.findAutoresVivosAnio(anio);

        opcionImprimirAutores(autoresVivos);
    }

    private void listarLibrosIdioma() {
        var menu = """
                --- MENU IDIOMA ---
                es - Español 
                en - Ingles
                fr - Frances
                pt - Portugues

                """;
        System.out.println(menu);
        System.out.println("Ingrese el idioma para buscar los libros: ");
        var opcionIdioma = teclado.nextLine();

        List<Libros> librosIdioma = librosRepositorio.findLibrosIdioma(opcionIdioma);

        opcionImprimirLibros(librosIdioma);
    }

    private void opcionImprimirLibros(List<Libros> libros) {
        if (!libros.isEmpty()) {
            libros.forEach(libro -> {
                Autores autor = libro.getAutor();
                Imprimir.imprimirLibro(libro, List.of(autor));
            });

        } else {
            System.out.println("\nNo se encontro informacion de libros para mostrar.\n");
        }
    }

    private void opcionImprimirAutores(List<Autores> autores) {
        if (!autores.isEmpty()) {
            autores.forEach(autor -> {
                List<Libros> libros = autor.getLibros();
                Imprimir.imprimirAutores(autor, libros);
            });

        } else {
            System.out.println("\nNo se encontro informacion de autores para mostrar.\n");
        }
    }


}