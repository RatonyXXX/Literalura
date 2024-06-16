package com.alurachallenge.literalura.model;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String titulo;
    private String idiomas;
    private String numeroDeDescargas;

//    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<Autores> autores;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autores autor;

    // Constructores
    public Libros() {}

    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        //this.idiomas = String.valueOf(datosLibros.idiomas());
        this.idiomas = String.join(", ", datosLibros.idiomas());
        this.numeroDeDescargas = String.valueOf(datosLibros.numeroDeDescargas());
    }

    // Getters y Setters
    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor = autor;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idioma) {
        this.idiomas = idioma;
    }

    public String getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(String numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    @Override
    public String toString() {
        return "Libros{" +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idiomas + '\'' +
                ", numeroDeDescargas='" + numeroDeDescargas + '\'' +
                                '}';
    }

}