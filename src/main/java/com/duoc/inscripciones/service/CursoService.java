package com.duoc.inscripciones.service;

import com.duoc.inscripciones.dto.CrearCursoRequest;
import com.duoc.inscripciones.dto.CursoDTO;
import com.duoc.inscripciones.dto.EntityMapper;
import com.duoc.inscripciones.model.Curso;
import com.duoc.inscripciones.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CursoService {

    private final CursoRepository cursoRepository;
    private final EntityMapper mapper;

    public CursoService(CursoRepository cursoRepository, EntityMapper mapper) {
        this.cursoRepository = cursoRepository;
        this.mapper = mapper;
    }

    public List<CursoDTO> findAll() {
        return mapper.toCursoDTOList(cursoRepository.findAll());
    }

    public CursoDTO crear(CrearCursoRequest req) {
        Curso curso = mapper.toEntity(req);
        return mapper.toDTO(cursoRepository.save(curso));
    }
}
