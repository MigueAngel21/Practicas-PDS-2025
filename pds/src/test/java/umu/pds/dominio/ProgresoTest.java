package umu.pds.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProgresoTest {

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
        progreso = new Progreso(curso);
    }

    @Test
    public void testConstructorConValoresIniciales() {
        Progreso p = new Progreso(5, 2, 3, curso);
        assertEquals(5, p.getRespuestasCorrectas());
        assertEquals(2, p.getRespuestasIncorrectas());
        assertEquals(3, p.getLastPregunta());
        assertEquals(curso, p.getCurso());
    }

    @Test
    public void testConstructorPorDefecto() {
        assertEquals(0, progreso.getRespuestasCorrectas());
        assertEquals(0, progreso.getRespuestasIncorrectas());
        assertEquals(0, progreso.getLastPregunta());
        assertEquals(curso, progreso.getCurso());
    }

    @Test
    public void testUpdateMultipleChoiceCorrecta() {
        progreso.update(1, true);
        assertEquals(1, progreso.getRespuestasCorrectas());
        assertEquals(0, progreso.getRespuestasIncorrectas());
        assertEquals(1, progreso.getLastPregunta());
    }

    @Test
    public void testUpdateMultipleChoiceIncorrecta() {
        progreso.update(1, false);
        assertEquals(0, progreso.getRespuestasCorrectas());
        assertEquals(1, progreso.getRespuestasIncorrectas());
        assertEquals(1, progreso.getLastPregunta());
    }

    @Test
    public void testUpdateFlashcard() {
        progreso.update(2);
        assertEquals(2, progreso.getLastPregunta());
    }

    @Test
    public void testToString() {
        assertEquals("Curso de fútbol", progreso.toString());
    }

    @Test
    public void testGetCompletitud() {
        progreso.update(1, true);
        int expectedCompletitud = (1 * 100) / curso.getNumPreguntas();
        assertEquals(expectedCompletitud, progreso.getCompletitud());
    }

}
