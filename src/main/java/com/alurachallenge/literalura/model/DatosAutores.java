package com.alurachallenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutores(
                @JsonAlias("name") String nombre,
                @JsonAlias("birth_year") String fechaDeNacimiento,
                @JsonAlias("death_year") String fechaDeFallecimiento) {
}