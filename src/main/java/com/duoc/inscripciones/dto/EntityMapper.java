package com.duoc.inscripciones.dto;

import com.duoc.inscripciones.model.Curso;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityMapper {

    // ----- Curso -----
    public CursoDTO toDTO(Curso curso) {
        if (curso == null) return null;
        return new CursoDTO(curso.getId(), curso.getNombre(), curso.getInstructor(),
                curso.getDuracion(), curso.getCosto());
    }

    public List<CursoDTO> toCursoDTOList(List<Curso> cursos) {
        return cursos.stream().map(this::toDTO).toList();
    }

    public Curso toEntity(CrearCursoRequest req) {
        return new Curso(null, req.getNombre(), req.getInstructor(), req.getDuracion(), req.getCosto());
    }

    // ----- Item de resumen de inscripcion -----
    public ItemResumenDTO toItem(Curso curso) {
        if (curso == null) return null;
        return new ItemResumenDTO(curso.getId(), curso.getNombre(), curso.getCosto());
    }
}
