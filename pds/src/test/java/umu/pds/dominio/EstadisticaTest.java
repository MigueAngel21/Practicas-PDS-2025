package umu.pds.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadisticaTest {

    private Estadistica estadistica;
    private Progreso progreso;
    private Curso curso;
    private EspecificacionCurso especificacion;
    private List<Pregunta> preguntas;

    @BeforeEach
    public void setUp() {
        preguntas = new ArrayList<>();
        preguntas.add(new MultipleChoice("Pregunta 1", "¿Cuántas champions league tiene el real madrid?", 3, 1, "15", "14", "13"));
        preguntas.add(new Flashcard("¿Quién es el jugador con más balones de oro?", "Lionel Messi"));
        
        especificacion = new EspecificacionCurso("Curso de fútbol", "Aprende sobre fútbol", preguntas);
        curso = new Curso(especificacion);
        progreso = new Progreso(5, 2, 3, curso);
        estadistica = new Estadistica(10, 3);
    }

    @Test
    public void testConstructorPorDefecto() {
        Estadistica e = new Estadistica();
        assertEquals(0, e.getTiempoDeUso());
        assertEquals(0, e.getRachaDeDias());
        assertNotNull(e.getProgresos());
        assertTrue(e.getProgresos().isEmpty());
    }

    @Test
    public void testConstructorConParametros() {
        assertEquals(10, estadistica.getTiempoDeUso());
        assertEquals(3, estadistica.getRachaDeDias());
        assertNotNull(estadistica.getProgresos());
        assertTrue(estadistica.getProgresos().isEmpty());
    }

    @Test
    public void testAñadirProgreso() {
        estadistica.añadirProgreso(progreso);
        assertEquals(1, estadistica.getProgresos().size());
        assertEquals(progreso, estadistica.getProgresos().get(0));
    }

    @Test
    public void testMultiplesProgresos() {
        Progreso progreso2 = new Progreso(3, 1, 2, curso);
        estadistica.añadirProgreso(progreso);
        estadistica.añadirProgreso(progreso2);
        
        assertEquals(2, estadistica.getProgresos().size());
        assertEquals(progreso, estadistica.getProgresos().get(0));
        assertEquals(progreso2, estadistica.getProgresos().get(1));
    }

}
