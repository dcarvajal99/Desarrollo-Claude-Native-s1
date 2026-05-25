package com.duoc.inscripciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class InscripcionRequest {

    @NotBlank(message = "estudiante is required")
    @Size(min = 2, max = 100, message = "estudiante must be between 2 and 100 characters")
    private String estudiante;

    @NotEmpty(message = "cursoIds must contain at least one course id")
    private List<Long> cursoIds;

    public InscripcionRequest() { }

    public InscripcionRequest(String estudiante, List<Long> cursoIds) {
        this.estudiante = estudiante;
        this.cursoIds = cursoIds;
    }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }
    public List<Long> getCursoIds() { return cursoIds; }
    public void setCursoIds(List<Long> cursoIds) { this.cursoIds = cursoIds; }
}
