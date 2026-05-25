package com.duoc.inscripciones.service;

import com.duoc.inscripciones.dto.EntityMapper;
import com.duoc.inscripciones.dto.InscripcionRequest;
import com.duoc.inscripciones.dto.ItemResumenDTO;
import com.duoc.inscripciones.dto.ResumenInscripcionDTO;
import com.duoc.inscripciones.exception.ResourceNotFoundException;
import com.duoc.inscripciones.model.Curso;
import com.duoc.inscripciones.model.Inscripcion;
import com.duoc.inscripciones.repository.CursoRepository;
import com.duoc.inscripciones.repository.InscripcionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InscripcionService {

    private final CursoRepository cursoRepository;
    private final InscripcionRepository inscripcionRepository;
    private final EntityMapper mapper;

    public InscripcionService(CursoRepository cursoRepository,
                              InscripcionRepository inscripcionRepository,
                              EntityMapper mapper) {
        this.cursoRepository = cursoRepository;
        this.inscripcionRepository = inscripcionRepository;
        this.mapper = mapper;
    }

    public ResumenInscripcionDTO inscribir(InscripcionRequest req) {
        List<ItemResumenDTO> items = new ArrayList<>();
        int total = 0;

        for (Long cursoId : req.getCursoIds()) {
            Curso curso = cursoRepository.findById(cursoId)
                    .orElseThrow(() -> ResourceNotFoundException.curso(cursoId));

            // persistir la inscripcion (una fila por curso)
            Inscripcion inscripcion = new Inscripcion(null, req.getEstudiante(), curso, LocalDateTime.now());
            inscripcionRepository.save(inscripcion);

            items.add(mapper.toItem(curso));
            total += curso.getCosto();
        }

        return new ResumenInscripcionDTO(req.getEstudiante(), items, total);
    }
}
