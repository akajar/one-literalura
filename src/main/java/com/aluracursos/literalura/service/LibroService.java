package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.LibroRepository;

import java.util.List;
import java.util.Optional;

public class LibroService {
    private final LibroRepository libroRepositorio;
    private final AutorService autorServicio;

    public LibroService(LibroRepository libroRepositorio, AutorService autorServicio) {
        this.libroRepositorio = libroRepositorio;
        this.autorServicio = autorServicio;
    }

    public List<Libro> listarLibros() {
        return libroRepositorio.findAll();
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepositorio.findByIdiomaIgnoreCase(idioma);
    }

    public Optional<Libro> buscarPorTitulo(String titulo) {
        return libroRepositorio.findByTituloIgnoreCase(titulo);
    }

    public Libro guardarLibro(String titulo, String idioma, int numeroDescargas, Autor autor) {
        // verificar si ya existe el libro
        Optional<Libro> existente = libroRepositorio.findByTituloIgnoreCase(titulo);
        if (existente.isPresent()) {
            return existente.get();
        }
        // verificar si el autor ya existe
        Autor autorExistente = autorServicio.buscarPorNombre(autor.getNombre());
        if (autorExistente == null) {
            autorExistente = autorServicio.guardarAutor(autor);
        }

        Libro libro = new Libro(titulo, idioma, numeroDescargas, autorExistente);
        return (Libro) libroRepositorio.save(libro);
    }
}
