package br.com.francelinodev.crudcursosspringboot.controller;

import br.com.francelinodev.crudcursosspringboot.model.Curso;
import br.com.francelinodev.crudcursosspringboot.repository.CursoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@AllArgsConstructor
public class CursosController {

    private final CursoRepository cursoRepository;

    @GetMapping
    public List<Curso> list() {
        return cursoRepository.findAll();
    }
}
