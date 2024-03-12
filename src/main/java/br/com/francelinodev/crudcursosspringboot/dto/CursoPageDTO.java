package br.com.francelinodev.crudcursosspringboot.dto;

import java.util.List;

public record CursoPageDTO(List<CursoDTO> cursos, long totalElements, long totalPages) {
}
