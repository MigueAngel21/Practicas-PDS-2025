package umu.pds.dominio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


public class CursoTest{

	private Curso curso;
	private EspecificacionCurso especificacion;
	private List<Pregunta> preguntas;
	private static final String PATHS = "umu.pds.dominio.estrategiasAprendizaje";

	@BeforeEach
	public void setUp() {
		preguntas = new ArrayList<>();
		preguntas.add(new MultipleChoice("Pregunta 1", "¿Cuántas champions league tiene el real madrid?", 3, 1, "15",
				"14", "13"));
		preguntas.add(new Flashcard("¿Quién es el jugador con más balones de oro?", "Lionel Messi"));

		especificacion = new EspecificacionCurso("Curso de fútbol", "Aprende sobre fútbol", preguntas);
		curso = new Curso(especificacion);
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + "Secuencial"));
	}

	static String[] estrategiasBase() {
		return new String[] { "Secuencial", "RepeticionEspaciada" };
	};

	static String[] estrategias() {
		return new String[] { "Secuencial", "Aleatoria", "RepeticionEspaciada" };
	};

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

	@ParameterizedTest
	@MethodSource("estrategiasBase")
	public void testGetSiguientePregunta(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Pregunta siguiente = curso.getSiguientePregunta();
		assertNotNull(siguiente);
		assertEquals("Pregunta 1", siguiente.getEnunciado());
	}

	@ParameterizedTest
	@MethodSource("estrategiasBase")
	public void testResponderPreguntaMultipleChoice(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Pregunta pregunta = curso.getSiguientePregunta();
		boolean resultado = curso.responderPregunta(pregunta, 1, 0);

		assertTrue(resultado); // Dependerá de la implementación interna de MultipleChoice
		assertEquals(1, curso.getNumPreguntas()); // Pregunta removida
	}

	@ParameterizedTest
	@MethodSource("estrategiasBase")
	public void testResponderPreguntaFlashcard(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Pregunta pregunta = curso.getSiguientePregunta();
		curso.responderPregunta(pregunta, 1, 0);

		assertEquals(1, curso.getNumPreguntas()); // Pregunta removida
	}

	@ParameterizedTest
	@MethodSource("estrategias")
	public void testUpdateProgreso(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Pregunta pregunta = curso.getSiguientePregunta();
		curso.responderPregunta(pregunta, 1, 0);

		assertNotNull(curso.getProgreso());
	}

	@ParameterizedTest
	@MethodSource("estrategias")
	public void testResponderTodasLasPreguntas(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		while (curso.getNumPreguntas() > 0) {
			Pregunta pregunta = curso.getSiguientePregunta();
			curso.responderPregunta(pregunta, 1, 0);
		}

		assertEquals(0, curso.getNumPreguntas());
	}

	@ParameterizedTest
	@MethodSource("estrategias")
	public void testIntentarObtenerPreguntaCuandoNoHay(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Pregunta p = curso.getSiguientePregunta();
		curso.responderPregunta(p, 1, 0);
		p = curso.getSiguientePregunta();
		curso.responderPregunta(p, 1, 1);

		assertNull(curso.getSiguientePregunta());
	}

	@ParameterizedTest
	@MethodSource("estrategias")
	public void testProgresoTrasResponderPreguntas(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Pregunta p = curso.getSiguientePregunta();
		curso.responderPregunta(p, 1, 0);
		p = curso.getSiguientePregunta();
		curso.responderPregunta(p, 1, 1);

		Progreso progreso = curso.getProgreso();
		assertNotNull(progreso);
	}

	@Test
	public void testEstrategiaRepeticionEspaciada() {
		curso.setEstrategia(
				FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + "RepeticionEspaciada"));
		Pregunta pregunta = curso.getSiguientePregunta();
		assertNotNull(pregunta);
		assertEquals("Pregunta 1", pregunta.getEnunciado());
		// Fallamos la pregunta, vuelve al final de la lista
		curso.responderPregunta(pregunta, 2, 0);

		pregunta = curso.getSiguientePregunta();
		assertNotNull(pregunta);
		assertEquals("¿Quién es el jugador con más balones de oro?", pregunta.getEnunciado());
		curso.responderPregunta(pregunta, 1, 1);

		// vuelve a pregunta 1

		pregunta = curso.getSiguientePregunta();
		assertNotNull(pregunta);
		assertEquals("Pregunta 1", pregunta.getEnunciado());
		curso.responderPregunta(pregunta, 1, 0);

		// no quedan más preguntas
		pregunta = curso.getSiguientePregunta();
		assertNull(pregunta);
	}

}
