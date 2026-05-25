package com.duoc.inscripciones.controller;

import com.duoc.inscripciones.dto.ApiResponse;
import com.duoc.inscripciones.dto.InscripcionRequest;
import com.duoc.inscripciones.dto.ResumenInscripcionDTO;
import com.duoc.inscripciones.service.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    // Endpoint 3: inscribir a un estudiante en uno o mas cursos -> resumen con total
    @PostMapping
    public ResponseEntity<ApiResponse<ResumenInscripcionDTO>> inscribir(@Valid @RequestBody InscripcionRequest req) {
        ResumenInscripcionDTO resumen = inscripcionService.inscribir(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created("Enrollment completed successfully", resumen));
    }
}
