package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dto.AutorDTO;
import com.aluracursos.literalura.dto.LibroDTO;
import com.aluracursos.literalura.dto.RespuestaDTO;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;

import java.util.Arrays;
import java.util.Optional;

public class GutendexService {
    private static final String BASE_URL = "https://gutendex.com/books?";
    private final ConvierteDatos conversor;
    private final LibroService libroServicio;

    public GutendexService(ConvierteDatos conversor, LibroService libroServicio) {
        this.conversor = conversor;
        this.libroServicio = libroServicio;
    }

    public Optional<Libro> buscarLibroPorTitulo(String titulo){
        try {
            String url = BASE_URL + "search=" + titulo.replace(" ","%20");
            RespuestaDTO respuesta = conversor.obtenerDatos(ConsumoAPI.obtenerDatos(url), RespuestaDTO.class);
            if (respuesta.results() == null || respuesta.results().isEmpty()) {
                System.out.println("No se encontró ningún libro con el título: " + titulo);
                return Optional.empty();
            }

            LibroDTO libroDto = respuesta.results().get(0);
            AutorDTO autorDto = libroDto.getAuthors().isEmpty() ? null : libroDto.getAuthors().get(0);

            String idioma = libroDto.getLanguages().isEmpty() ? "Desconocido" : libroDto.getLanguages().get(0);

            if (autorDto == null) {
                System.out.println("El libro no tiene autor registrado.");
                Autor autor = null;
            }else{
                Autor autor = new Autor(
                        autorDto.getName(),
                        autorDto.getBirthYear(),
                        autorDto.getDeathYear()
                );
            }

            Libro libro = libroServicio.guardarLibro(
                    libroDto.getTitle(),
                    idioma,
                    libroDto.getDownloadCount(),
                    null
            );

            return Optional.of(libro);
        }catch (Exception e){
            System.out.println("Error al procesar la consulta: " + e.getMessage());
            return Optional.empty();
        }
    }
}
