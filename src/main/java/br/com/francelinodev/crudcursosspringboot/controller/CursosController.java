package br.com.francelinodev.crudcursosspringboot.controller;

import br.com.francelinodev.crudcursosspringboot.model.Curso;
import br.com.francelinodev.crudcursosspringboot.repository.CursoRepository;
import br.com.francelinodev.crudcursosspringboot.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Valid
@RestController
@RequestMapping("/api/cursos")
public class CursosController {

    private final CursoService cursoService;

    public CursosController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> list() {
        return cursoService.list();
    }

    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody @Valid Curso curso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.create(curso));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable @NotNull @Positive Long id) {
        return cursoService.findById(id).map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable @Valid @NotNull @Positive Long id, @RequestBody Curso curso) {
        return cursoService.update(id, curso)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        if (cursoService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }
}
