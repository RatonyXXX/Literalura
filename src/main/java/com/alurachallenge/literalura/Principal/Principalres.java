//package com.alurachallenge.literalura.Principal;
//
//import com.alurachallenge.literalura.common.Imprimir;
//import com.alurachallenge.literalura.model.*;
//import com.alurachallenge.literalura.repository.AutoresRepository;
//import com.alurachallenge.literalura.repository.LibrosRepository;
//import com.alurachallenge.literalura.service.ConsumoAPI;
//import com.alurachallenge.literalura.service.ConvierteDatos;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class Principalres {
//    private Scanner teclado = new Scanner(System.in);
//    private static final String URL = "https://gutendex.com/books/";
//    private ConsumoAPI consumoAPI = new ConsumoAPI();
//    private ConvierteDatos convertir = new ConvierteDatos();
//
//    private LibrosRepository librosRepositorio;
//    private AutoresRepository autoresRepositorio;
//    private List<Libros> libros;
//    private List<Autores> autores;
//
//    public Principalres(LibrosRepository librosRepository, AutoresRepository autoresRepository) {
//        this.librosRepositorio = librosRepository;
//        this.autoresRepositorio = autoresRepository;
//    }
//
//    public void mostrarMenu() {
//
//        while (true) {
//            var menu = """
//                    -------------- CHALLENGE LITERALURA --------------
//                    1 - Buscar libro por titulo (WEB)
//                    2 - Listar libros registrados
//                    3 - Listar autores registrados
//                    4 - Listar autores vivos en un determinado año
//                    5 - Listar libros por idioma
//
//                    0 - Salir
//                    """;
//            System.out.println(menu);
//
//            try {
//                String input = teclado.nextLine();
//                int opcion = Integer.parseInt(input);
//
//                switch (opcion) {
//                    case 1:
//                        buscarLibroWeb();
//                        break;
//                    case 2:
//                        listarLibros();
//                        break;
//                    case 3:
//                        listarAutores();
//                        break;
//                    case 4:
////                    listarAutoresAnio();
//                        break;
//                    case 5:
////                    listarIdioma();
//                        break;
//                    case 6:
////                    buscarSeriesPorCategoria();
//                        break;
//                    case 0:
//                        System.out.println("Cerrando la aplicación...\n");
//                        break;
//                    default:
//                        System.out.println("Opción inválida. Intente nuevamente.\n");
//                }
//            } catch(NumberFormatException e){
//                System.out.println("Entrada no válida. Por favor, ingrese un número.\n");
//            }
//        }
//    }
//
//    private void buscarLibroWeb() {
//        System.out.println("Buscar libro por titulo:");
//        var tituloLibro = teclado.nextLine();
//        var json = consumoAPI.obtenerDatos(URL + "?search=" + tituloLibro.replace(" ", "+"));
//        var datos = convertir.obtenerDatos(json, Datos.class);
//
//        if (datos != null && datos.resultados() != null && !datos.resultados().isEmpty()) {
//            datos.resultados()
//                    .stream()
//                    .findFirst()
//                    .ifPresent(resultado -> {
//                        // Crear instancia de Libros
//                        Libros libro = new Libros(new DatosLibros(
//                                resultado.titulo(),
//                                resultado.autores(),
//                                resultado.idiomas(),
//                                resultado.numeroDeDescargas()
//                        ));
//
//                        // Obtiene el autor de la BD si existe
//                        if (!resultado.autores().isEmpty()) {
//                            // Toma ----------solo el primer autor
//                            DatosAutores datosAutor = resultado.autores().get(0);
//
//
//                            Autores autor = autoresRepositorio.findByNombre(datosAutor.nombre())
//                                    .orElse(new Autores(datosAutor));
//
//                            // Agregar libro a lista de libros del autor
//                            autor.getLibros().add(libro);
//                            libro.setAutor(autor);
//
//                            // Guardar autor y libro en la BD
//                            try {
//                                autoresRepositorio.save(autor);
//                                Imprimir.imprimirLibro(libro, List.of(libro.getAutor()));
//                            } catch (DataIntegrityViolationException e) {
//                                // Manejar la excepción, por ejemplo:
//                                System.out.println("\nEl libro ya existe en la base de datos.\n");
//                            }
//                        }
//                        else {
//                            // Informar al usuario que no se puede guardar el libro sin autor
//                            System.out.println("\nNo se puede guardar el libro porque no tiene autores especificados.\n");
//                        }
//
//
//
//
////                        // Obtiene el autor de la BD si existe
////                        for (DatosAutores datosAutor : resultado.autores()) {
////                            Autores autor = autoresRepositorio.findByNombre(datosAutor.nombre())
////                                    .orElse(new Autores(datosAutor));
////
////                            // Agrega libro a lista de libros del autor
////                            autor.getLibros().add(libro);
////                            libro.setAutor(autor);
////
////                            // Guardar autor y libro en la BD
////                            //autoresRepositorio.save(autor);
////
////                            // Imprimir Libro
////                            //Comunes.imprimirLibro(libro, List.of(libro.getAutor()));
////
////                            try {
////                                autoresRepositorio.save(autor);
////                                Comunes.imprimirLibro(libro, List.of(libro.getAutor()));
////                            } catch (DataIntegrityViolationException e) {
////                                // Manejar la excepción, por ejemplo:
////                                System.out.println("\nEl libro ya existe en la base de datos.\n");
////                            }
////                        }
//
//
//
//                        // En el método buscarLibroWeb() después de crear la instancia de Libros
//
////                        Libros existingLibro = librosRepositorio.findByTitulo(libro.getTitulo());
////                        if (existingLibro != null) {
////                            System.out.println("El libro con título '" + libro.getTitulo() + "' ya existe en la base de datos.");
////                            return;
////                        }
////
////                        // Guardar el libro solo si no existe un libro con el mismo título
////                        librosRepositorio.save(libro);
//
//
//
//
//
//                        //Comunes.imprimirLibro(libro, libro.getAutor() != null ? List.of(libro.getAutor()) : List.of());
////                        if (libro.getAutor() != null) {
////                            Comunes.imprimirLibro(libro, List.of(libro.getAutor()));
////                        } else {
////                            Comunes.imprimirLibro(libro, List.of());
////                        }
//
//
//                    });
//        } else {
//            System.out.println("No se encontraron datos.\n");
//        }
//    }
//
//    private void listarLibros() {
//        List<Libros> libros = librosRepositorio.findAll();
//
//        libros.sort((libro1, libro2) -> libro1.getTitulo().compareToIgnoreCase(libro2.getTitulo()));
//
//        //libros.sort(Comparator.comparing(Libros::getTitulo, String.CASE_INSENSITIVE_ORDER));
//
//        libros.forEach(libro -> {
//            Autores autor = libro.getAutor();
//            Imprimir.imprimirLibro(libro, List.of(autor));
//        });
//
//    }
//
//    private void listarAutores() {
//        List<Autores> autores = autoresRepositorio.findAll();
//
//        autores.sort((autor1, autor2) -> autor1.getNombre().compareToIgnoreCase(autor2.getNombre()));
//
//        //autores.sort(Comparator.comparing(Autores::getNombre, String.CASE_INSENSITIVE_ORDER));
//
//        autores.forEach(autor -> {
//            List<Libros> libros = autor.getLibros();
//            Imprimir.imprimirAutores(autor, libros);
//        });
//
//    }
//
//}