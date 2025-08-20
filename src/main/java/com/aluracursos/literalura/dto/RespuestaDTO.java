package com.aluracursos.literalura.dto;

import java.util.List;

public record RespuestaDTO (
        int count,
        String next,
        String previous,
        List<LibroDTO> results
){
}
