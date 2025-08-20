package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    //Uso de JPQL
    // autores con añoNacimiento <= :anio y (anioMuerte es null o anioMuerte >= :anio)
    @Query("SELECT a FROM autores a " +
            "WHERE a.anioNacimiento <= :anio " +
            "AND (a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)")
    List<Autor> buscarAutoresVivosEnAnio(int anio);

    // listar todos los autores ordenados por nombre
    @Query("SELECT a FROM autores a ORDER BY a.nombre ASC")
    List<Autor> listarAutoresOrdenados();
}
