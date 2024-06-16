package com.alurachallenge.literalura.repository;

import com.alurachallenge.literalura.model.Autores;
import com.alurachallenge.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores, Long> {

    @Query("SELECT a FROM Autores a JOIN FETCH a.libros WHERE a.nombre = :nombre")
    Optional<Autores> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autores a WHERE a.fechaDeNacimiento <= :anio AND (a.fechaDeFallecimiento IS NULL OR a.fechaDeFallecimiento > :anio) ORDER BY a.nombre")
    List<Autores> findAutoresVivosAnio(int anio);

}
