package umu.pds.controlador;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import umu.pds.dominio.Curso;
import umu.pds.dominio.Estadistica;
import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.FactoriaEstrategias;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;
import umu.pds.dominio.RepositorioUsuarios;
import umu.pds.dominio.Usuario;

public class Controlador {
	
	// Singleton
	private static Controlador instance = null;
	
	private Usuario usuarioActual;
	private RepositorioUsuarios repositorioUsuarios;
	private Curso cursoActual; 
	private Pregunta preguntaActual;
	private static final String PATHS = "umu.pds.dominio.estrategiasAprendizaje";
	
	private Map<Curso,Progreso> progresos = new java.util.HashMap<Curso,Progreso>();
	
	public static Controlador getUnicaInstancia() {
		if (instance == null) {
	        instance = new Controlador();
	    }
	    return instance;
	}
	
	private Controlador() {
		this.repositorioUsuarios = RepositorioUsuarios.getUnicaInstancia();
	}
	
	
	// Constructor para testing
	@SuppressFBWarnings("SING_SINGLETON_HAS_NONPRIVATE_CONSTRUCTOR")
	protected Controlador(RepositorioUsuarios repositorioUsuarios) {
		this.repositorioUsuarios = repositorioUsuarios;
	}
	
	
	public boolean iniciarSesion(String usuario, String contrasena) {
		// remove spaces from user input
		usuarioActual = repositorioUsuarios.getUsuario(usuario);
		
		if (usuarioActual != null && usuarioActual.comprobarContrasena(contrasena)) {
			return true;
		}
		
		return false;
	}
	
	public void setCursoActual(Curso curso) {
		this.cursoActual = curso;
	    this.setEstregiaAprendizaje("Secuencial");
		this.preguntaActual = cursoActual.getSiguientePregunta();
	}
	
	// TODO: Poner protected una vez tengamos persistencia y no haga falta pruebas con datos predefinidos 
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
		repositorioUsuarios.update(usuarioActual);
		
	}
	
	public Estadistica getEstadisticas() {
		return usuarioActual.getEstadisticas();
	}
	
	public String getUsername() {
		return usuarioActual.getNombre();
	}
	
	public List<String> getEstrategiasAprendizaje() {
		// Conseguir una lista con el nombre de todas las clases Estrategia
		// Para esto vamos a usar reflexion pero como Java solo nos permite
		// introspeccion utilizamos la libreria Reflections
		Reflections reflections = new Reflections(PATHS);
		Set<Class<? extends EstrategiaApredizaje>> allClasses = reflections.getSubTypesOf(EstrategiaApredizaje.class);
		List<String> estrategias = allClasses.stream().map(c -> c.getSimpleName()).collect(Collectors.toList());
		return estrategias;
	}
	
	public void setEstregiaAprendizaje(String estrategia) {
		EstrategiaApredizaje e = FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + '.' + estrategia);
		cursoActual.setEstrategia(e);
		
	}

	
}
