package br.com.francelinodev.crudcursosspringboot.dto.mapper;

import br.com.francelinodev.crudcursosspringboot.dto.CursoDTO;
import br.com.francelinodev.crudcursosspringboot.dto.LessonDTO;
import br.com.francelinodev.crudcursosspringboot.enums.Category;
import br.com.francelinodev.crudcursosspringboot.model.Curso;
import br.com.francelinodev.crudcursosspringboot.model.Lesson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CursoMapperTest {

    @Test
    public void test_toDTO_returnsCursoDTOWithCorrectAttributes() {

        CursoMapper cursoMapper = new CursoMapper();
        Curso curso = new Curso();
        curso.setId(1L);
        curso.setName("Curso 1");
        curso.setCategory(Category.BACKEND);
        List<Lesson> lessons = new ArrayList<>();
        Lesson lesson1 = new Lesson();
        lesson1.setId(1L);
        lesson1.setName("Lesson 1");
        lesson1.setYoutubeUrl("https://www.youtube.com/lesson1");
        Lesson lesson2 = new Lesson();
        lesson2.setId(2L);
        lesson2.setName("Lesson 2");
        lesson2.setYoutubeUrl("https://www.youtube.com/lesson2");
        lessons.add(lesson1);
        lessons.add(lesson2);
        curso.setLessons(lessons);

        CursoDTO cursoDTO = cursoMapper.toDTO(curso);

        Assertions.assertEquals(curso.getId(), cursoDTO.id());
        Assertions.assertEquals(curso.getName(), cursoDTO.name());
        Assertions.assertEquals(curso.getCategory().getValue(), cursoDTO.category());
        Assertions.assertEquals(curso.getLessons().size(), cursoDTO.lessons().size());
        for (int i = 0; i < curso.getLessons().size(); i++) {
            Lesson lesson = curso.getLessons().get(i);
            LessonDTO lessonDTO = cursoDTO.lessons().get(i);
            Assertions.assertEquals(lesson.getId(), lessonDTO.id());
            Assertions.assertEquals(lesson.getName(), lessonDTO.name());
            Assertions.assertEquals(lesson.getYoutubeUrl(), lessonDTO.youtubeUrl());
        }
    }

    @Test
    public void test_toDTO_returnsNullWhenGivenNullCursoObject() {
        CursoMapper cursoMapper = new CursoMapper();
        Curso curso = null;

        CursoDTO cursoDTO = cursoMapper.toDTO(curso);

        Assertions.assertNull(cursoDTO);
    }


    @Test
    public void test_toDTO_returnsCursoDTOWithCorrectAttributesWhenGivenCursoObjectWithEmptyName() {
        // Arrange
        CursoMapper cursoMapper = new CursoMapper();
        Curso curso = new Curso();
        curso.setName("");
        curso.setCategory(Category.FRONTEND);

        // Act
        CursoDTO cursoDTO = cursoMapper.toDTO(curso);

        // Assert
        Assertions.assertEquals(curso.getId(), cursoDTO.id());
        Assertions.assertEquals(curso.getName(), cursoDTO.name());
        Assertions.assertEquals(curso.getCategory().getValue(), cursoDTO.category());
    }

    @Test
    public void test_toEntity_returnsCursoObjectWithCorrectAttributesWhenGivenCursoDTOObjectWithNullId() {
        CursoMapper cursoMapper = new CursoMapper();
        CursoDTO cursoDTO = new CursoDTO(null, "Curso 1", "Back-end", new ArrayList<>());

        Curso curso = cursoMapper.toEntity(cursoDTO);

        Assertions.assertNull(curso.getId());
        Assertions.assertEquals(cursoDTO.name(), curso.getName());
        Assertions.assertEquals(Category.BACKEND, curso.getCategory());
    }

}