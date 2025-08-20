package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por título
    @Query("SELECT lb FROM libros lb WHERE lb.titulo LIKE %:titulo%")
    Optional<Libro> findByTituloIgnoreCase(String titulo);

    //Uso de derived queries
    // Buscar libros por idioma
    List<Libro> findByIdiomaIgnoreCase(String idioma);
}
