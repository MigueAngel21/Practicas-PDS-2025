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
        
        especificacion = new EspecificacionCurso("Curso de fútbol", "Aprende sobre fútbol","imagen", preguntas);
        // Crear una estrategia de aprendizaje ficticia
		EstrategiaAprendizaje estrategia = FactoriaEstrategias.getUnicaInstancia()
				.crearEstrategia("umu.pds.dominio.estrategiasAprendizaje.Secuencial");
        curso = new Curso(especificacion, estrategia);
        progreso = new Progreso(curso);
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
        estadistica.addProgreso(progreso);
        assertEquals(1, estadistica.getProgresos().size());
        assertEquals(progreso, estadistica.getProgresos().get(0));
    }

    @Test
    public void testMultiplesProgresos() {
        Progreso progreso2 = new Progreso(curso);
        estadistica.addProgreso(progreso);
        // Sobreescribimos el progreso
        estadistica.addProgreso(progreso2);
        
        assertEquals(1, estadistica.getProgresos().size());
        assertEquals(progreso2, estadistica.getProgresos().get(0));
    }

    @Test 
    public void testMultiplesProgesosDiferentes() {
		Progreso progreso2 = new Progreso(curso);
		Curso curso2 = new Curso(
                new EspecificacionCurso("Curso de historia", "Aprende sobre historia", "imagen", preguntas),
                curso.getEstrategia());
		Progreso progreso3 = new Progreso(curso2);
		estadistica.addProgreso(progreso);
		estadistica.addProgreso(progreso2);
		estadistica.addProgreso(progreso3);

		assertEquals(2, estadistica.getProgresos().size());
		assertEquals(progreso2, estadistica.getProgresos().get(0));
		assertEquals(progreso3, estadistica.getProgresos().get(1));
		assertEquals(curso, progreso2.getCurso());
		assertEquals(curso2, progreso3.getCurso());
    }
    
}
