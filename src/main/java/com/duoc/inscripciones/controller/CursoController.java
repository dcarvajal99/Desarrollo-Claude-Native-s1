package com.duoc.inscripciones.controller;

import com.duoc.inscripciones.dto.ApiResponse;
import com.duoc.inscripciones.dto.CrearCursoRequest;
import com.duoc.inscripciones.dto.CursoDTO;
import com.duoc.inscripciones.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    // Endpoint 1: listar cursos disponibles (nombre, instructor, duracion, costo)
    @GetMapping
    public ApiResponse<List<CursoDTO>> findAll() {
        return ApiResponse.ok("List of courses retrieved successfully", cursoService.findAll());
    }

    // Endpoint 2: agregar un curso nuevo a la oferta
    @PostMapping
    public ResponseEntity<ApiResponse<CursoDTO>> crear(@Valid @RequestBody CrearCursoRequest req) {
        CursoDTO created = cursoService.crear(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Course created successfully", created));
    }
}
