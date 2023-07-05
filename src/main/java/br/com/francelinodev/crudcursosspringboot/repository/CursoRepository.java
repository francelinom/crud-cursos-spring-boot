package br.com.francelinodev.crudcursosspringboot.repository;

import br.com.francelinodev.crudcursosspringboot.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
