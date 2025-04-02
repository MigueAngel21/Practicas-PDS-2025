package umu.pds.controlador;

import java.util.Map;

import umu.pds.dominio.Curso;
import umu.pds.dominio.Flashcard;
import umu.pds.dominio.LibreriaCursos;
import umu.pds.dominio.MultipleChoice;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;
import umu.pds.dominio.RepositorioUsuarios;
import umu.pds.dominio.Usuario;

public enum Controlador {
	
	// Singleton con ENUM
	INSTANCE;
	
	private Usuario usuarioActual;
	private RepositorioUsuarios repositorioUsuarios;
	private Curso cursoActual = new Curso(LibreriaCursos.INSTANCE.getCurso("Capitales de Europa"));
	private Pregunta preguntaActual;
	
	private Map<Curso,Progreso> progresos = new java.util.HashMap<Curso,Progreso>();
	
	
	public boolean InciarSesion(String usuario, String contrasena) {
		System.out.println("Usuario: " + usuario + " Contraseña: " + contrasena);
		if (usuario.equals("paco") && contrasena.equals("1234") ) {
			return true;
		} else {
			return false;
		}
		
		/*usuarioActual = repositorioUsuarios.getUsuario(usuario);
		
		if (usuarioActual != null && usuarioActual.comprobarContrasena(contrasena)) {
			return true;
		}
		
		return false;*/
	}
	
	public void setCusoActual(Curso curso) {
		this.cursoActual = curso;
	}
	
	public Curso getCursoActual() {
		return cursoActual;
	}
	
	public Pregunta getSiguientePregunta() {
		preguntaActual = cursoActual.getSiguientePregunta();
		return preguntaActual;
    }
	
	public void responderPregunta(int respuesta, int numPregunta) {
		cursoActual.responderPregunta((MultipleChoice) preguntaActual,respuesta, numPregunta);
	}
	
	public void responderPregunta(int numPregunta) {
		cursoActual.responderPregunta((Flashcard) preguntaActual, numPregunta);
	}

	
}
