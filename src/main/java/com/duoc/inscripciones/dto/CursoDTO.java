package com.duoc.inscripciones.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {

    private Long id;
    private String nombre;
    private String instructor;
    private String duracion;
    private int costo;

    public CursoDTO() { }

    public CursoDTO(Long id, String nombre, String instructor, String duracion, int costo) {
        this.id = id;
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracion = duracion;
        this.costo = costo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    public int getCosto() { return costo; }
    public void setCosto(int costo) { this.costo = costo; }
}
