package br.com.francelinodev.crudcursosspringboot.dto.mapper;

import br.com.francelinodev.crudcursosspringboot.dto.CursoDTO;
import br.com.francelinodev.crudcursosspringboot.enums.Category;
import br.com.francelinodev.crudcursosspringboot.model.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoDTO toDTO(Curso curso) {
        if (curso == null) {
            return null;
        }
        return new CursoDTO(curso.getId(), curso.getName(), "Front-end");
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
        curso.setCategory(Category.FRONTEND);
        curso.setStatus("Ativo");

        return curso;
    }
}
