package umu.pds.controlador;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import umu.pds.dominio.Curso;
import umu.pds.dominio.EspecificacionCurso;
import umu.pds.dominio.Estadistica;
import umu.pds.dominio.EstrategiaAprendizaje;
import umu.pds.dominio.FactoriaEstrategias;
import umu.pds.dominio.GoogleOAuthAdapter;
import umu.pds.dominio.LibreriaCursos;
import umu.pds.dominio.OAuthProvider;
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
	private OAuthProvider provider;

	private Map<Curso, Progreso> progresos = new java.util.HashMap<Curso, Progreso>();

	
	public static Controlador getUnicaInstancia() {
		if (instance == null) {
			instance = new Controlador();
		}
		return instance;
	}

	private Controlador() {
		this.repositorioUsuarios = RepositorioUsuarios.getUnicaInstancia();
	}

	public void setOAuthProvider(String provider) {
		if (provider.equals("Google")) {
			this.provider = new GoogleOAuthAdapter();
		} else {
			throw new IllegalArgumentException("Unsupported provider: " + provider);
		}
	}

	public void cargarCursosJSON(String path) {
		LibreriaCursos.getInstance().cargarCursos(path);
	}

	// Constructor para testing
	@SuppressFBWarnings("SING_SINGLETON_HAS_NONPRIVATE_CONSTRUCTOR")
	protected Controlador(RepositorioUsuarios repositorioUsuarios) {
		this.repositorioUsuarios = repositorioUsuarios;
	}

	public boolean iniciarSesion(String usuario, String contrasena) {
		// remove spaces from user input
		usuarioActual = repositorioUsuarios.getUsuario(usuario);

		if (usuarioActual != null && usuarioActual.comprobarLogin(contrasena)) {
			// Rellenar mapa de progresos
			loadUserData();
			return true;
		}

		return false;
	}

	private void loadUserData() {
		// TODO: fix
		List<Progreso> progresos = usuarioActual.getEstadisticas().getProgresos();
		for (Progreso progreso : progresos) {
			// Como el curso no persiste en BD tenemos que crearlo con el nombre y la
			// estrategia
			EspecificacionCurso especificacion = LibreriaCursos.getInstance()
					.getEspecificacionCurso(progreso.getNombreCurso());
			// Puede ser que se haya borrado el curso
			if (especificacion == null) {
				continue;
			}
			EstrategiaAprendizaje estrategia = FactoriaEstrategias.getUnicaInstancia()
					.crearEstrategia(PATHS + '.' + progreso.getEstrategia());
			Curso curso = new Curso(especificacion, estrategia, progreso);
			this.progresos.put(curso, progreso);
			progreso.setCurso(curso);
		}
	}

	public boolean thirdPartyIniciarSesion() {
		provider.authenticate();
		String accessToken;
		try {
			accessToken = provider.getAccessToken();
		} catch (Exception e) {
			return false;
		}
		List<String> userInfo;
		try {
			userInfo = provider.getUserInfo(accessToken);
		} catch (Exception e) {
			return false;
		}
		// Necesitamos nombre, email y provider_id
		String nombre = userInfo.get(0);
		String email = userInfo.get(1);
		String provider_id = userInfo.get(2);
		if (repositorioUsuarios.getUsuario(email) == null) {
			usuarioActual = new Usuario(nombre, email, provider_id);
			repositorioUsuarios.add(usuarioActual);
		} else {
			usuarioActual = repositorioUsuarios.getUsuario(email);
		}

		if (usuarioActual != null && usuarioActual.comprobarLogin(provider_id)) {
			loadUserData();
			return true;
		}
		return true;
	}

	public void setCursoActual(Curso curso) {
		this.cursoActual = curso;
	}

	public void setCursoActual(EspecificacionCurso curso, String estrategia) {
		EstrategiaAprendizaje e = FactoriaEstrategias.getUnicaInstancia().crearEstrategia(PATHS + '.' + estrategia);
		Curso c = new Curso(curso, e);
		if (progresos.containsKey(c)) {
			cursoActual = progresos.get(c).getCurso();
		} else {
			cursoActual = c;
			progresos.put(cursoActual, new Progreso(cursoActual));
		}
	}

	// TODO: Poner protected una vez tengamos persistencia y no haga falta pruebas
	// con datos predefinidos
	public void setUsuarioActual(Usuario usuario) {
		this.usuarioActual = usuario;
	}

	public int getLastPregunta() {
		if (cursoActual.getProgreso() == null) {
			return 1;
		}
		return cursoActual.getLastPregunta();
	}

	public Curso getCursoActual() {
		return cursoActual;
	}

	// Devuelve la pregunta actual (realmente para que se muestre la siguiente
	// tienes que antes responder)
	public Pregunta getSiguientePregunta() {
		preguntaActual = cursoActual.getSiguientePregunta(progresos.get(cursoActual));
		if (preguntaActual == null) {
			resetearCurso();
		}
		return preguntaActual;
	}

	private void resetearCurso() {
		cursoActual.resetearCurso();
	}

	public void responderPregunta(int respuesta) {
		cursoActual.responderPregunta(preguntaActual, respuesta);
		// Actualizamos el progreso del curso
		Progreso progreso = cursoActual.getProgreso();
		progresos.put(cursoActual, progreso);
		// Actualizamos la estadistica del usuario
		usuarioActual.updateEstadisticas(progreso);
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
		Set<Class<? extends EstrategiaAprendizaje>> allClasses = reflections.getSubTypesOf(EstrategiaAprendizaje.class);
		List<String> estrategias = allClasses.stream().map(c -> c.getSimpleName()).collect(Collectors.toList());
		return estrategias;
	}

	public void registrarUsuario(String nombre, String email, char[] contraseña, String edad) {
		int edadInt = Integer.parseInt(edad);
		Usuario usuario = new Usuario(nombre, email, String.valueOf(contraseña), edadInt);
		// Comprobar si el usuario ya existe
		if (repositorioUsuarios.getUsuario(usuario.getEmail()) != null) {
			throw new IllegalArgumentException("El usuario ya existe");
		}
		repositorioUsuarios.add(usuario);
	}

	public LibreriaCursos getLibreriaCursos() {
		return LibreriaCursos.getInstance();
	}

}
