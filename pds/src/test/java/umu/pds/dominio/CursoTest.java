package umu.pds.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

		especificacion = new EspecificacionCurso("Curso de fútbol", "Aprende sobre fútbol", "imagen", preguntas);
		EstrategiaAprendizaje estrategia = FactoriaEstrategias.getUnicaInstancia()
				.crearEstrategia(PATHS + "." + "Secuencial");
		curso = new Curso(especificacion, estrategia);
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
		Progreso p = curso.getProgreso();
		Pregunta siguiente = curso.getSiguientePregunta(p);
		assertNotNull(siguiente);
		assertEquals("Pregunta 1", siguiente.getEnunciado());
	}

	@ParameterizedTest
	@MethodSource("estrategiasBase")
	public void testResponderPreguntaMultipleChoice(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Progreso p = curso.getProgreso();
		Pregunta pregunta = curso.getSiguientePregunta(p);
		boolean resultado = curso.responderPregunta(pregunta, 1);

		assertTrue(resultado); // Dependerá de la implementación interna de MultipleChoice
		assertInstanceOf(MultipleChoice.class, pregunta);
	}

	@ParameterizedTest
	@MethodSource("estrategiasBase")
	public void testResponderPreguntaFlashcard(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Progreso p = curso.getProgreso();
		Pregunta pregunta = curso.getSiguientePregunta(p);
		curso.responderPregunta(pregunta, 1);
		pregunta = curso.getSiguientePregunta(p);
		
		assertInstanceOf(Flashcard.class, pregunta);
	}

	@ParameterizedTest
	@MethodSource("estrategias")
	public void testUpdateProgreso(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Progreso p = curso.getProgreso();
		Pregunta pregunta = curso.getSiguientePregunta(p);
		curso.responderPregunta(pregunta, 1);

		assertNotNull(curso.getProgreso());
	}

	@ParameterizedTest
	@MethodSource("estrategias")
	public void testResponderTodasLasPreguntas(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Progreso p = curso.getProgreso();
		Pregunta pregunta = curso.getSiguientePregunta(p);
		while (pregunta != null) {
			curso.responderPregunta(pregunta, 1);
			pregunta = curso.getSiguientePregunta(p);
		}

		pregunta = curso.getSiguientePregunta(p);
		// Curso reseteado
		assertNotNull(pregunta);
	}

	@ParameterizedTest
	@MethodSource("estrategias")
	public void testIntentarObtenerPreguntaCuandoNoHay(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Progreso prog = curso.getProgreso();
		Pregunta p = curso.getSiguientePregunta(prog);
		curso.responderPregunta(p, 1);
		p = curso.getSiguientePregunta(prog);
		curso.responderPregunta(p, 1);

		assertNull(curso.getSiguientePregunta(prog));
	}

	@ParameterizedTest
	@MethodSource("estrategias")
	public void testProgresoTrasResponderPreguntas(String estrategia) {
		curso.setEstrategia(FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + estrategia));
		Progreso prog = curso.getProgreso();
		Pregunta p = curso.getSiguientePregunta(prog);
		curso.responderPregunta(p, 1);
		p = curso.getSiguientePregunta(prog);
		curso.responderPregunta(p, 1);

		Progreso progreso = curso.getProgreso();
		assertNotNull(progreso);
	}

	@Test
	public void testEstrategiaRepeticionEspaciada() {
		curso.setEstrategia(
				FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + "." + "RepeticionEspaciada"));
		Progreso prog = curso.getProgreso();
		Pregunta pregunta = curso.getSiguientePregunta(prog);
		assertNotNull(pregunta);
		assertEquals("Pregunta 1", pregunta.getEnunciado());
		// Fallamos la pregunta, vuelve al final de la lista
		curso.responderPregunta(pregunta, 2);

		pregunta = curso.getSiguientePregunta(prog);
		assertNotNull(pregunta);
		assertEquals("¿Quién es el jugador con más balones de oro?", pregunta.getEnunciado());
		curso.responderPregunta(pregunta, 1);

		// vuelve a pregunta 1

		pregunta = curso.getSiguientePregunta(prog);
		assertNotNull(pregunta);
		assertEquals("Pregunta 1", pregunta.getEnunciado());
		curso.responderPregunta(pregunta, 1);

		// no quedan más preguntas
		pregunta = curso.getSiguientePregunta(prog);
		assertNull(pregunta);
	}
	

}
