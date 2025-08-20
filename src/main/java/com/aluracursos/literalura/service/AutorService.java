package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.repository.AutorRepository;

import java.util.List;

public class AutorService {
    private final AutorRepository autorRepositorio;

    public AutorService(AutorRepository autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    public List<Autor> listarAutores() {
        return autorRepositorio.listarAutoresOrdenados();
    }

    public List<Autor> listarAutoresVivosEnAnio(int anio) {
        return autorRepositorio.buscarAutoresVivosEnAnio(anio);
    }

    public Autor buscarPorNombre(String nombre) {
        List<Autor> autores = autorRepositorio.findAll();
        return autores.stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre))
                .findFirst().orElse(null);
    }

    public Autor guardarAutor(Autor autor) {
        return (Autor) autorRepositorio.save(autor);
    }
}
