package umu.pds.controlador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import umu.pds.dominio.EspecificacionCurso;
import umu.pds.dominio.LibreriaCursos;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.RepositorioUsuarios;

// Estos test se apoyan 
class ControladorTestBD {

	private Controlador controlador;
	private RepositorioUsuarios repositorioUsuarios;

	@BeforeEach
	@SuppressFBWarnings("NP_NULL_ON_SOME_PATH_EXCEPTION")
	void setUp() {

	    // Crear archivo temporal
		File tempDb = null;
		try {
			tempDb = File.createTempFile("test-db-", ".sqlite");
		} catch (Exception e) {
			e.printStackTrace();
		}
	    tempDb.deleteOnExit(); // Se borra automáticamente al cerrar la JVM (opcional)

	    // Usar ese archivo como base de datos y así no interferir con la base de datos de verdad
	    String jdbcUrl = "jdbc:sqlite:" + tempDb.getAbsolutePath();

        Map<String, String> overrideProps = new HashMap<>();
        overrideProps.put("jakarta.persistence.jdbc.url", jdbcUrl);
        overrideProps.put("hibernate.hbm2ddl.auto", "create-drop");
        overrideProps.put("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");

       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia", overrideProps);
        EntityManager entityManager = emf.createEntityManager();
        
		repositorioUsuarios = new RepositorioUsuarios(emf, entityManager);
		// Es mejor crear un nuevo controlador en vez de usar la unica instacia cada vez para que se resetee el estado del controlador entre test y test
		controlador = new Controlador(repositorioUsuarios);
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
	
	static String[] estrategiasNoRepeticion() {
		return new String[] { "Secuencial", "Aleatoria" };
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
	
	
	@ParameterizedTest
	@MethodSource("estrategiasNoRepeticion")
	void testRehacerCurso(String estrategia) {
		EspecificacionCurso esp = LibreriaCursos.getInstance().getEspecificacionCurso("Curso de fútbol");
		controlador.setCursoActual(esp, estrategia);

		Pregunta p = controlador.getSiguientePregunta();
		while (p != null) {
			controlador.responderPregunta(1);
			p = controlador.getSiguientePregunta();
		}

		// Una vez terminado el curso lo hacemos entero otra vez
		assertNull(p);

		assertEquals(controlador.getPuntosLastCurso(), 95);
		p = controlador.getSiguientePregunta();
		// Debe de haberse reseteado
		assertNotNull(p);
		assertEquals(controlador.getPuntos(), 95);
		while (p != null) {
			controlador.responderPregunta(1);
			p = controlador.getSiguientePregunta();
		}
		assertEquals(controlador.getPuntosLastCurso(), 95);
		assertEquals(controlador.getPuntos(), 190);
	}

	@Test
	void testRehacerCursoRepeticionEspaciada() {
		EspecificacionCurso esp = LibreriaCursos.getInstance().getEspecificacionCurso("Curso de fútbol");
		controlador.setCursoActual(esp, "RepeticionEspaciada");

		int[] correctas = { 1, 1, 0, 0, 0, 1, 0, 2, 1, 2, 1, 2, 1 };

		Pregunta p = controlador.getSiguientePregunta();
		int i = 0;
		while (p != null) {
			controlador.responderPregunta(correctas[i]);
			p = controlador.getSiguientePregunta();
			i++;
		}

		// Una vez terminado el curso lo hacemos entero otra vez
		assertNull(p);

		assertEquals(controlador.getPuntosLastCurso(),130);
		p = controlador.getSiguientePregunta();
		assertNotNull(p);
		assertEquals(controlador.getPuntos(),130);

	    i = 0;
		while (p != null) {
			controlador.responderPregunta(correctas[i]);
			p = controlador.getSiguientePregunta();
			i++;
		}
		
		assertNull(p);
		assertEquals(controlador.getPuntos(),260);
		assertEquals(controlador.getPuntosLastCurso(),130);

	}


}
