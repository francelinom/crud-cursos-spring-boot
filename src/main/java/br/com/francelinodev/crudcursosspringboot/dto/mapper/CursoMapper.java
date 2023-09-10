package br.com.francelinodev.crudcursosspringboot.dto.mapper;

import br.com.francelinodev.crudcursosspringboot.dto.CursoDTO;
import br.com.francelinodev.crudcursosspringboot.dto.LessonDTO;
import br.com.francelinodev.crudcursosspringboot.enums.Category;
import br.com.francelinodev.crudcursosspringboot.model.Curso;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        List<LessonDTO> lessons = curso.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .collect(Collectors.toList());

        return new CursoDTO(curso.getId(), curso.getName(), curso.getCategory().getValue(), lessons);
    }

    public Curso toEntity(CursoDTO cursoDTO) {

        if (cursoDTO == null) {
            return null;
        }

        Curso curso = new Curso();
        if (cursoDTO.id() != null) {
            curso.setId(cursoDTO.id());
        }

        curso.setName(cursoDTO.name());
        curso.setCategory(converterCategoryValue(cursoDTO.category()));

        return curso;
    }

    public Category converterCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Front-end" -> Category.FRONTEND;
            case "Back-end" -> Category.BACKEND;
            default -> throw new IllegalArgumentException("Valor inválido: " + value);
        };
    }
}
