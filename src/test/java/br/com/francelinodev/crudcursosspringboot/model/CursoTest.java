package br.com.francelinodev.crudcursosspringboot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.francelinodev.crudcursosspringboot.enums.Category;
import br.com.francelinodev.crudcursosspringboot.enums.Status;
import org.junit.jupiter.api.Test;

class CursoTest {

    @Test
    void testCanEqual() {
        assertFalse((new Curso()).canEqual("Other"));
    }


    @Test
    void testCanEqual2() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertTrue(curso.canEqual(curso2));
    }


    @Test
    void testConstructor() {
        assertEquals(Status.ATIVO, (new Curso()).getStatus());
    }


    @Test
    void testEquals() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);
        assertNotEquals(curso, null);
    }


    @Test
    void testEquals2() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);
        assertNotEquals(curso, "Different type to Curso");
    }


    @Test
    void testEquals3() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);
        assertEquals(curso, curso);
        int expectedHashCodeResult = curso.hashCode();
        assertEquals(expectedHashCodeResult, curso.hashCode());
    }


    @Test
    void testEquals4() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertEquals(curso, curso2);
        int expectedHashCodeResult = curso.hashCode();
        assertEquals(expectedHashCodeResult, curso2.hashCode());
    }


    @Test
    void testEquals5() {
        Curso curso = new Curso();
        curso.setCategory(null);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertNotEquals(curso, curso2);
    }


    @Test
    void testEquals6() {
        Curso curso = new Curso();
        curso.setCategory(Category.FRONTEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertNotEquals(curso, curso2);
    }

    @Test
    void testEquals7() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(2L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertNotEquals(curso, curso2);
    }


    @Test
    void testEquals8() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(null);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertNotEquals(curso, curso2);
    }


    @Test
    void testEquals9() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName(null);
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertNotEquals(curso, curso2);
    }

    @Test
    void testEquals10() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("br.com.francelinodev.crudcursosspringboot.model.Curso");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertNotEquals(curso, curso2);
    }


    @Test
    void testEquals11() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(null);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertNotEquals(curso, curso2);
    }


    @Test
    void testEquals12() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.INATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertNotEquals(curso, curso2);
    }


    @Test
    void testEquals13() {
        Curso curso = new Curso();
        curso.setCategory(null);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(null);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertEquals(curso, curso2);
        int expectedHashCodeResult = curso.hashCode();
        assertEquals(expectedHashCodeResult, curso2.hashCode());
    }


    @Test
    void testEquals14() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(null);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(null);
        curso2.setName("Name");
        curso2.setStatus(Status.ATIVO);
        assertEquals(curso, curso2);
        int expectedHashCodeResult = curso.hashCode();
        assertEquals(expectedHashCodeResult, curso2.hashCode());
    }


    @Test
    void testEquals15() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName(null);
        curso.setStatus(Status.ATIVO);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName(null);
        curso2.setStatus(Status.ATIVO);
        assertEquals(curso, curso2);
        int expectedHashCodeResult = curso.hashCode();
        assertEquals(expectedHashCodeResult, curso2.hashCode());
    }


    @Test
    void testEquals16() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(null);

        Curso curso2 = new Curso();
        curso2.setCategory(Category.BACKEND);
        curso2.setId(1L);
        curso2.setName("Name");
        curso2.setStatus(null);
        assertEquals(curso, curso2);
        int expectedHashCodeResult = curso.hashCode();
        assertEquals(expectedHashCodeResult, curso2.hashCode());
    }


    @Test
    void testSetCategory() {
        Curso curso = new Curso();
        curso.setCategory(Category.BACKEND);
        curso.setId(1L);
        curso.setName("Name");
        curso.setStatus(Status.ATIVO);
        String actualToStringResult = curso.toString();
        Category actualCategory = curso.getCategory();
        Long actualId = curso.getId();
        String actualName = curso.getName();
        Status actualStatus = curso.getStatus();
        assertEquals(Category.BACKEND, actualCategory);
        assertEquals(1L, actualId.longValue());
        assertEquals("Name", actualName);
        assertEquals(Status.ATIVO, actualStatus);
        assertEquals("Curso(id=1, name=Name, category=Back-end, status=Ativo)", actualToStringResult);
    }
}

