package com.duoc.inscripciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CrearCursoRequest {

    @NotBlank(message = "nombre is required")
    @Size(min = 2, max = 150, message = "nombre must be between 2 and 150 characters")
    private String nombre;

    @NotBlank(message = "instructor is required")
    @Size(min = 2, max = 100, message = "instructor must be between 2 and 100 characters")
    private String instructor;

    @NotBlank(message = "duracion is required")
    @Size(min = 2, max = 50, message = "duracion must be between 2 and 50 characters")
    private String duracion;

    @Positive(message = "costo must be greater than 0")
    private int costo;

    public CrearCursoRequest() { }

    public CrearCursoRequest(String nombre, String instructor, String duracion, int costo) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracion = duracion;
        this.costo = costo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public String getDuracion() { return duracion; }
    public void setDuracion(String duracion) { this.duracion = duracion; }
    public int getCosto() { return costo; }
    public void setCosto(int costo) { this.costo = costo; }
}
