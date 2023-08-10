package br.com.francelinodev.crudcursosspringboot.service;

import br.com.francelinodev.crudcursosspringboot.dto.CursoDTO;
import br.com.francelinodev.crudcursosspringboot.dto.mapper.CursoMapper;
import br.com.francelinodev.crudcursosspringboot.model.Curso;
import br.com.francelinodev.crudcursosspringboot.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoDTO> list() {
        return cursoRepository.findAll()
                .stream()
                .map(cursoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CursoDTO findById(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toDTO)
                .orElse(null);
    }

    public CursoDTO create(@Valid @NotNull CursoDTO curso) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    public CursoDTO update(@Valid @NotNull @Positive Long id, CursoDTO curso) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    recordFound.setName(curso.name());
                    recordFound.setCategory(curso.category());
                    return cursoMapper.toDTO(cursoRepository.save(recordFound));
                }).orElseThrow(() -> new RuntimeException("Curso não encontrado" + id));
    }

    public boolean delete(@PathVariable @NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    cursoRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
