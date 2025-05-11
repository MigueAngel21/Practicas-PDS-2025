package umu.pds.controlador;

import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import umu.pds.dominio.EspecificacionCurso;
import umu.pds.dominio.LibreriaCursos;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.RepositorioUsuarios;

class ControladorTestBD {

	
	private Controlador controlador;
	private RepositorioUsuarios repositorioUsuarios;
	
	
	@BeforeEach
	void setUp() {
		controlador = Controlador.getUnicaInstancia();
		repositorioUsuarios = RepositorioUsuarios.getUnicaInstancia();
		controlador.cargarCursosJSON("cursos");
		char[] contrasena = {'1','2','3','4'};
		controlador.registrarUsuario("Paco", "paco@um.es", contrasena, "88");
		assertTrue(controlador.iniciarSesion("paco@um.es", "1234"));
	}
	
	@AfterEach
	void tearDown() {
		repositorioUsuarios.getAllUsuarios().forEach(usuario -> repositorioUsuarios.remove(usuario));
	}
	
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

}
