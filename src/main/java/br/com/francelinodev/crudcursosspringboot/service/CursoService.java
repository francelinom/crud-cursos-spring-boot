package br.com.francelinodev.crudcursosspringboot.service;

import br.com.francelinodev.crudcursosspringboot.dto.CursoDTO;
import br.com.francelinodev.crudcursosspringboot.dto.CursoPageDTO;
import br.com.francelinodev.crudcursosspringboot.dto.mapper.CursoMapper;
import br.com.francelinodev.crudcursosspringboot.model.Curso;
import br.com.francelinodev.crudcursosspringboot.repository.CursoRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
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

    public CursoPageDTO list(@PositiveOrZero int page, @Positive @Max(100) int size) {
        Page<Curso> pageCurso = cursoRepository.findAll(PageRequest.of(page, size));
        List<CursoDTO> cursos = pageCurso.get().map(cursoMapper::toDTO).collect(Collectors.toList());
        return new CursoPageDTO(cursos, pageCurso.getTotalElements(), pageCurso.getTotalPages());
    }

//    public List<CursoDTO> list() {
//        return cursoRepository.findAll()
//                .stream()
//                .map(cursoMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    public CursoDTO findById(@NotNull @Positive Long id) {
        return cursoRepository.findById(id).map(cursoMapper::toDTO)
                .orElse(null);
    }

    public CursoDTO create(@Valid @NotNull CursoDTO curso) {
        return cursoMapper.toDTO(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    public CursoDTO update(@Valid @NotNull @Positive Long id, CursoDTO cursoDTO) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    Curso curso = cursoMapper.toEntity(cursoDTO);
                    recordFound.setName(cursoDTO.name());
                    recordFound.setCategory(cursoMapper.converterCategoryValue(cursoDTO.category()));
                    recordFound.getLessons().clear();
                    curso.getLessons().forEach(recordFound.getLessons()::add);
                    return cursoMapper.toDTO(cursoRepository.save(recordFound));
                }).orElseThrow(() -> new RuntimeException("Curso não encontrado" + id));
    }

    public boolean delete(@NotNull @Positive Long id) {
        return cursoRepository.findById(id)
                .map(recordFound -> {
                    cursoRepository.deleteById(id);
                    return true;
                })
                .orElse(false);
    }
}
