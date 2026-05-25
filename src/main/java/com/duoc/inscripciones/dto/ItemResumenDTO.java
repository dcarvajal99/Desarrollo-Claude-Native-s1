package com.duoc.inscripciones.dto;

public class ItemResumenDTO {

    private Long cursoId;
    private String nombre;
    private int costo;

    public ItemResumenDTO() { }

    public ItemResumenDTO(Long cursoId, String nombre, int costo) {
        this.cursoId = cursoId;
        this.nombre = nombre;
        this.costo = costo;
    }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getCosto() { return costo; }
    public void setCosto(int costo) { this.costo = costo; }
}
