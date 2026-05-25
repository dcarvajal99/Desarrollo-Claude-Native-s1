package com.duoc.inscripciones;

import com.duoc.inscripciones.dto.InscripcionRequest;
import com.duoc.inscripciones.dto.ResumenInscripcionDTO;
import com.duoc.inscripciones.service.InscripcionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InscripcionServiceTest {

    @Autowired
    private InscripcionService inscripcionService;

    @Test
    void inscribir_calculaTotalComoSumaDeCostos() {
        // Cursos 1 (120000) y 2 (180000) del data.sql -> total 300000
        InscripcionRequest req = new InscripcionRequest("Ana Tester", List.of(1L, 2L));

        ResumenInscripcionDTO resumen = inscripcionService.inscribir(req);

        assertEquals("Ana Tester", resumen.getEstudiante());
        assertEquals(2, resumen.getCursos().size());
        assertEquals(300000, resumen.getTotal());
    }
}
