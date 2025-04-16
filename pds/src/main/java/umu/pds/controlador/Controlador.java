package umu.pds.controlador;

import java.util.Map;

import umu.pds.dominio.Curso;
import umu.pds.dominio.Estadistica;
import umu.pds.dominio.Flashcard;
import umu.pds.dominio.MultipleChoice;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;
import umu.pds.dominio.RepositorioUsuarios;
import umu.pds.dominio.Usuario;

public class Controlador {
	
	// Singleton
	private static Controlador instance = new Controlador();
	
	private Usuario usuarioActual;
	private RepositorioUsuarios repositorioUsuarios;
	private Curso cursoActual; 
	private Pregunta preguntaActual;
	
	private Map<Curso,Progreso> progresos = new java.util.HashMap<Curso,Progreso>();
	
	public static Controlador getUnicaInstancia() {
		return instance;
	}
	
	public Controlador() {
		this.repositorioUsuarios = new RepositorioUsuarios();
	}
	
	// Constructor para testing
	public Controlador(RepositorioUsuarios repositorioUsuarios) {
		this.repositorioUsuarios = repositorioUsuarios;
	}
	
	
	public boolean iniciarSesion(String usuario, String contrasena) {
		// remove spaces from user input
		usuario = usuario.trim();
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
	
	public void setCursoActual(Curso curso) {
		this.cursoActual = curso;
		this.preguntaActual = cursoActual.getSiguientePregunta();
	}
	
	// TODO: Poner protected una vez tengamos persistencia 
	public void setUsuarioActual(Usuario usuario) {
		this.usuarioActual = usuario;
	}
	
	
	public Curso getCursoActual() {
		return cursoActual;
	}
	
	// Devuelve la pregunta actual (realmente para que se muestre la siguiente tienes que antes responder)
	public Pregunta getSiguientePregunta() {
		preguntaActual = cursoActual.getSiguientePregunta();
		return preguntaActual;
    }
	
	public void responderPregunta(int respuesta, int numPregunta) {
		cursoActual.responderPregunta(preguntaActual,respuesta, numPregunta);
	}
	
	public Estadistica getEstadisticas() {
		return usuarioActual.getEstadisticas();
	}
	
	public String getUsername() {
		return usuarioActual.getNombre();
	}

	
}
