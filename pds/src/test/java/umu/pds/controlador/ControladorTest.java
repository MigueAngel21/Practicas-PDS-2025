package umu.pds.controlador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import umu.pds.dominio.Curso;
import umu.pds.dominio.EspecificacionCurso;
import umu.pds.dominio.Estadistica;
import umu.pds.dominio.EstrategiaAprendizaje;
import umu.pds.dominio.Flashcard;
import umu.pds.dominio.MultipleChoice;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.RepositorioUsuarios;
import umu.pds.dominio.Usuario;
import umu.pds.dominio.estrategiasAprendizaje.Secuencial;

class ControladorTest {
	private Controlador controlador;
	private RepositorioUsuarios repositorioUsuariosMock;
	private Usuario usuario;
	private Curso curso;
	private List<Pregunta> preguntas;

	@BeforeEach
	public void setUp() {
		// Mock del repositorio de usuarios
		repositorioUsuariosMock = Mockito.mock(RepositorioUsuarios.class);

		// Crear instancias reales de Usuario y Curso
		usuario = new Usuario("Paco", "paco@example.com", "1234", 25);

		preguntas = new ArrayList<>();
		preguntas.add(
				new MultipleChoice("Pregunta 1", "¿Cuántas Champions tiene el Real Madrid?", 3, 1, "15", "14", "13"));
		preguntas.add(new Flashcard("¿Quién es el jugador con más balones de oro?", "Lionel Messi"));

		EspecificacionCurso especificacion = new EspecificacionCurso("Curso de Fútbol", "Aprende sobre fútbol",
				"imagen", preguntas);
		EstrategiaAprendizaje estrategia = new Secuencial();
		curso = new Curso(especificacion, estrategia);

		// Instanciar el Controlador con el mock del repositorio
		controlador = new Controlador(repositorioUsuariosMock);
		controlador.setUsuarioActual(usuario);
	}

	@Test
	public void testIniciarSesion_UsuarioCorrecto() {
		when(repositorioUsuariosMock.getUsuario("paco")).thenReturn(usuario);

		boolean resultado = controlador.iniciarSesion("paco", "1234");

		assertTrue(resultado, "El usuario debería haber iniciado sesión correctamente.");
	}

	@Test
	public void testIniciarSesion_UsuarioIncorrecto() {
		when(repositorioUsuariosMock.getUsuario("desconocido")).thenReturn(null);

		boolean resultado = controlador.iniciarSesion("desconocido", "password");

		assertFalse(resultado, "El inicio de sesión debería fallar con credenciales incorrectas.");
	}

	@Test
	public void testSetCursoActual() {
		controlador.setCursoActual(curso);
		assertEquals(curso, controlador.getCursoActual(), "El curso actual debería ser el asignado.");
	}

	@Test
	public void testGetSiguientePregunta() {
		controlador.setCursoActual(curso);
		Pregunta siguientePregunta = controlador.getSiguientePregunta();

		assertNotNull(siguientePregunta, "La pregunta no debería ser nula.");
		assertEquals("Pregunta 1", siguientePregunta.getEnunciado(), "La primera pregunta debería coincidir.");
	}

	@Test
	public void testResponderPregunta_MultipleChoice_Correcta() {
		controlador.setCursoActual(curso);
		Pregunta pregunta = controlador.getSiguientePregunta();

		assertInstanceOf(MultipleChoice.class, pregunta);
		MultipleChoice multipleChoice = (MultipleChoice) pregunta;

		boolean resultado = multipleChoice.esCorrecta(1); // Respuesta correcta
		assertTrue(resultado, "La respuesta debería ser correcta.");
	}

	@Test
	public void testResponderPregunta_Flashcard() {
		controlador.setCursoActual(curso);
		Pregunta pregunta = controlador.getSiguientePregunta(); // Segunda pregunta (Flashcard)
		controlador.responderPregunta(1);
		assertInstanceOf(MultipleChoice.class, pregunta);
		pregunta = controlador.getSiguientePregunta(); // Pregunta siguiente
		; // Primera pregunta (MultipleChoice)

		assertInstanceOf(Flashcard.class, pregunta);
		controlador.responderPregunta(1); // Simula la respuesta
		pregunta = controlador.getSiguientePregunta(); // Pregunta siguiente

		assertNull(pregunta);
	}

	@Test
	public void testGetEstadisticas() {
		controlador.setUsuarioActual(usuario);
		Estadistica estadisticas = controlador.getEstadisticas();

		assertNotNull(estadisticas, "Las estadísticas del usuario no deberían ser nulas.");
	}

	@Test
	public void testGetUsername() {
		controlador.setUsuarioActual(usuario);
		assertEquals("Paco", controlador.getUsername(), "El nombre de usuario debería ser Paco.");
	}
	
	@Test
	public void estrategiaNoExiste() {
		// Crear un curso con una estrategia no válida
		EspecificacionCurso especificacion = new EspecificacionCurso("Curso de Fútbol", "Aprende sobre fútbol",
				"imagen", preguntas);
		
		assertThrows(NullPointerException.class, () -> {
			controlador.setCursoActual(especificacion, "EstrategiaInvalida");
		});
	}
	
	@Test
	public void testGetEstrategiasAprendizaje() {
		List<String> estrategias = controlador.getEstrategiasAprendizaje();
		assertNotNull(estrategias, "La lista de estrategias no debería ser nula.");
		assertEquals(3, estrategias.size());
		for (String estrategia : estrategias) {
			assertFalse(estrategia.isEmpty(), "El nombre de la estrategia no debería estar vacío.");
		}
	}
	
	@Test
	public void testGetLastPregunta() {
		controlador.setCursoActual(curso);
		Pregunta pregunta = controlador.getSiguientePregunta();
		controlador.responderPregunta(1); // Simula la respuesta
		int lastPregunta = controlador.getLastPregunta();
		assertEquals(2, lastPregunta, "El número de la última pregunta debería ser 2.");
	}
	
	@Test
	public void testGetLastPreguntaSinProgreso() {
		controlador.setCursoActual(curso);
		int lastPregunta = controlador.getLastPregunta();
		assertEquals(1, lastPregunta, "El número de la última pregunta debería ser 1 si no hay progreso.");
	}
	
	@Test
	public void testSetOAuthProvider() {
		// Comprobamos que google funciona pero otros no
		controlador.setOAuthProvider("Google");
		assertThrows(IllegalArgumentException.class, () -> {
			controlador.setOAuthProvider("InvalidProvider");
		});
	}
	
}
