package br.com.francelinodev.crudcursosspringboot.controller;

import br.com.francelinodev.crudcursosspringboot.dto.CursoDTO;
import br.com.francelinodev.crudcursosspringboot.service.CursoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    public List<CursoDTO> list() {
        return cursoService.list();
    }

    @PostMapping
    public CursoDTO create(@RequestBody @Valid @NotNull CursoDTO curso) {
        return cursoService.create(curso);
    }

    @GetMapping("/{id}")
    public CursoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return cursoService.findById(id);
    }

    @PutMapping("/{id}")
    public CursoDTO update(@PathVariable @Valid @NotNull @Positive Long id, @RequestBody CursoDTO curso) {
        return cursoService.update(id, curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id) {
        if (cursoService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }
}
