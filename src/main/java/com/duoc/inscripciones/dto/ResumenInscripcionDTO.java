package com.duoc.inscripciones.dto;

import java.util.List;

public class ResumenInscripcionDTO {

    private String estudiante;
    private List<ItemResumenDTO> cursos;
    private int total;

    public ResumenInscripcionDTO() { }

    public ResumenInscripcionDTO(String estudiante, List<ItemResumenDTO> cursos, int total) {
        this.estudiante = estudiante;
        this.cursos = cursos;
        this.total = total;
    }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }
    public List<ItemResumenDTO> getCursos() { return cursos; }
    public void setCursos(List<ItemResumenDTO> cursos) { this.cursos = cursos; }
    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }
}
