package umu.pds.dominio;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class CursoTest extends TestCase {

		private Curso curso;
	    private EspecificacionCurso especificacion;
	    private List<Pregunta> preguntas;

	
	@BeforeEach
	public void setUp() {
		preguntas = new ArrayList<>();
		preguntas.add(new MultipleChoice("Pregunta 1", "¿Cuántas champions league tiene el real madrid?", 3, 1, "15",
				"14", "13"));
		preguntas.add(new Flashcard("¿Quién es el jugador con más balones de oro?", "Lionel Messi"));

		especificacion = new EspecificacionCurso("Curso de fútbol", "Aprende sobre fútbol", preguntas);
		curso = new Curso(especificacion);
	}

	@Test
	public void testGetEspecificacionCurso() {
		assertEquals(especificacion, curso.getEspecificacionCurso());
	}

	@Test
	public void testGetNombre() {
		assertEquals("Curso de fútbol", curso.getNombre());
	}

	@Test
	public void testGetNumPreguntas() {
		assertEquals(2, curso.getNumPreguntas());
	}

	@Test
	public void testGetSiguientePregunta() {
		Pregunta siguiente = curso.getSiguientePregunta();
		assertNotNull(siguiente);
		assertEquals("Pregunta 1", siguiente.getEnunciado());
	}

	@Test
	public void testResponderPreguntaMultipleChoice() {
		Pregunta pregunta = curso.getSiguientePregunta();
		boolean resultado = curso.responderPregunta(pregunta, 1, 0);

		assertTrue(resultado); // Dependerá de la implementación interna de MultipleChoice
		assertEquals(1, curso.getNumPreguntas()); // Pregunta removida
	}

	@Test
	public void testResponderPreguntaFlashcard() {
		Pregunta pregunta = curso.getSiguientePregunta();
		curso.responderPregunta(pregunta, 1, 0);

		assertEquals(1, curso.getNumPreguntas()); // Pregunta removida
	}

	@Test
	public void testUpdateProgreso() {
		Pregunta pregunta = curso.getSiguientePregunta();
		curso.responderPregunta(pregunta, 1, 0);

		assertNotNull(curso.getProgreso());
	}
	
    @Test
    public void testResponderTodasLasPreguntas() {
        while (curso.getNumPreguntas() > 0) {
            Pregunta pregunta = curso.getSiguientePregunta();
                curso.responderPregunta(pregunta, 1, 0);
            }
    
        assertEquals(0, curso.getNumPreguntas());
    }
    
    @Test
    public void testIntentarObtenerPreguntaCuandoNoHay() {
    	Pregunta p = curso.getSiguientePregunta();
        curso.responderPregunta(p, 1, 0);
        p = curso.getSiguientePregunta();
        curso.responderPregunta(p, 1, 1);
        
        assertNull(curso.getSiguientePregunta());
    }
    
    @Test
    public void testProgresoTrasResponderPreguntas() {
    	Pregunta p = curso.getSiguientePregunta();
        curso.responderPregunta(p, 1, 0);
        p = curso.getSiguientePregunta();
        curso.responderPregunta(p, 1, 1);
        
        Progreso progreso = curso.getProgreso();
        assertNotNull(progreso);
    }
}
