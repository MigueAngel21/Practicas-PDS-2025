package umu.pds.controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import umu.pds.dominio.EspecificacionCurso;
import umu.pds.dominio.LibreriaCursos;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.RepositorioUsuarios;

// Para que los test funcioen en necesario que no exista BD (o que la que exista haya sido creada por los propios test porque estará vacia)
class ControladorTestBD {

	private Controlador controlador;
	private RepositorioUsuarios repositorioUsuarios;

	@BeforeEach
	void setUp() {

		controlador = Controlador.getUnicaInstancia();
		repositorioUsuarios = RepositorioUsuarios.getUnicaInstancia();
		// borrar todo
		repositorioUsuarios.getAllUsuarios().forEach(usuario -> repositorioUsuarios.remove(usuario));
		controlador.cargarCursosJSON("cursos");
		char[] contrasena = { '1', '2', '3', '4' };
		controlador.registrarUsuario("Paco", "paco@um.es", contrasena, "88");
		assertTrue(controlador.iniciarSesion("paco@um.es", "1234"));
	}
	
	static String[] estrategias() {
		return new String[] { "Secuencial", "Aleatoria", "RepeticionEspaciada" };
	};

	@Test
	void testAñadirUsuarios() {
		assertFalse(controlador.iniciarSesion("paco@um.es", "mal"));
		assertTrue(controlador.iniciarSesion("paco@um.es", "1234"));
	}

	@Test
	void testResolverCurso() {

		EspecificacionCurso esp = LibreriaCursos.getInstance().getEspecificacionCurso("Curso de fútbol");
		controlador.setCursoActual(esp, "Secuencial");

		Pregunta p = controlador.getSiguientePregunta();
		while (p != null) {
			controlador.responderPregunta(1);
			p = controlador.getSiguientePregunta();
		}

		assertEquals(controlador.getCursoActual().getProgreso().getCompletitud(), 100);
		assertEquals(controlador.getCursoActual().getProgreso().getRespuestasCorrectas(), 6);
		assertEquals(controlador.getCursoActual().getProgreso().getRespuestasIncorrectas(), 7);

	}

	@Test
	void testgetLastPregunta() {
		EspecificacionCurso esp = LibreriaCursos.getInstance().getEspecificacionCurso("Curso de fútbol");
		controlador.setCursoActual(esp, "Secuencial");

		Pregunta p = controlador.getSiguientePregunta();
		for (int i = 1; i <= 5; i++) {
			controlador.responderPregunta(i);
			p = controlador.getSiguientePregunta();
		}

		assertEquals(controlador.getCursoActual().getProgreso().getLastPregunta(), 6);
	}

	@Test
	void testEstadisticas() {
		// Resolvemos el curso
		EspecificacionCurso esp = LibreriaCursos.getInstance().getEspecificacionCurso("Curso de fútbol");
		controlador.setCursoActual(esp, "Secuencial");

		Pregunta p = controlador.getSiguientePregunta();
		while (p != null) {
			controlador.responderPregunta(1);
			p = controlador.getSiguientePregunta();
		}

		// Revisamos las estadisticas
		assertEquals(controlador.getEstadisticas().getTiempoDeUso(), 0);
		assertEquals(controlador.getEstadisticas().getRachaDeDias(), 0);
		assertEquals(controlador.getEstadisticas().getProgresos().size(), 1);
		assertEquals(controlador.getEstadisticas().getProgresos().get(0).getCurso().getNombre(), "Curso de fútbol");
		assertEquals(controlador.getEstadisticas().getProgresos().get(0).getCompletitud(), 100);
	}

	@Test
	void testRegistrarUsuarioYaExiste() {
		char[] contrasena = { 'a', 's', 'd', 'a' };
		assertThrows(IllegalArgumentException.class, () -> controlador.registrarUsuario("Paco", "paco@um.es", contrasena, "88"));
	}
	
	@Test
	void testVolverAInciarSesion() {
		// Simulamos que el usuario se desconecta (como no hay un cerrar sesión propiamente dicho realmente llamados de nuevo a iniciarSesion)
		EspecificacionCurso esp = LibreriaCursos.getInstance().getEspecificacionCurso("Curso de fútbol");
		controlador.setCursoActual(esp, "Secuencial");

		Pregunta p = controlador.getSiguientePregunta();
		while (p != null) {
			controlador.responderPregunta(1);
			p = controlador.getSiguientePregunta();
		}
		
		// Con esto cargamos de nuevo de la base de datos el usuario
		assertTrue(controlador.iniciarSesion("paco@um.es", "1234"));
		// Revisamos las estadisticas que se han cargado ahora de BD
		assertEquals(controlador.getEstadisticas().getTiempoDeUso(), 0);
		assertEquals(controlador.getEstadisticas().getRachaDeDias(), 0);
		assertEquals(controlador.getEstadisticas().getProgresos().size(), 1);
		assertEquals(controlador.getEstadisticas().getProgresos().get(0).getCurso().getNombre(), "Curso de fútbol");
		assertEquals(controlador.getEstadisticas().getProgresos().get(0).getCompletitud(), 100);
		assertEquals(controlador.getEstadisticas().getProgresos().get(0).getRespuestasCorrectas(), 6);
		assertEquals(controlador.getEstadisticas().getProgresos().get(0).getRespuestasIncorrectas(), 7);
		
	}
	
	@ParameterizedTest
	@MethodSource("estrategias")
	void testReabrirCurso(String estrategia) {
		// Simulamos que el usuario se desconecta (como no hay un cerrar sesión
		// propiamente dicho realmente llamados de nuevo a iniciarSesion)
		EspecificacionCurso esp = LibreriaCursos.getInstance().getEspecificacionCurso("Curso de fútbol");
		controlador.setCursoActual(esp, estrategia);

		Pregunta p = controlador.getSiguientePregunta();
		for (int i = 1; i <= 5; i++) {
			controlador.responderPregunta(1);
			p = controlador.getSiguientePregunta();
		}

		assertEquals(controlador.getEstadisticas().getProgresos().size(), 1);

		controlador.iniciarSesion("paco@um.es", "1234");
		controlador.setCursoActual(esp, estrategia);

		assertEquals(controlador.getEstadisticas().getProgresos().size(), 1);
		assertEquals(controlador.getEstadisticas().getProgresos().get(0).getCurso().getNombre(), "Curso de fútbol");
		assertEquals(controlador.getEstadisticas().getProgresos().get(0).getCompletitud(), 38);
		
		for (int i = 1; i <= 8; i++) {
			controlador.responderPregunta(1);
			p = controlador.getSiguientePregunta();
		}
		
		// No tienen que quedar más preguntas
		assertNull(p);
	}

}
