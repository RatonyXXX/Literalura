package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Autores;
import com.alurachallenge.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libros, Long> {

    @Query("SELECT l FROM Libros l WHERE l.idiomas LIKE %:idioma%")
    List<Libros> findLibrosIdioma(String idioma);

}
