package umu.pds.dominio;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Curso {

	private EstrategiaAprendizaje estrategia;
	private EspecificacionCurso especificacionCurso;
	private Progreso progreso;
	private Map<Integer, Pregunta> mapPreguntas;
	private Map<Pregunta, Integer> mapIndexPreguntas;

	public Curso(EspecificacionCurso especificacionCurso, EstrategiaAprendizaje estrategia) {
		this.especificacionCurso = especificacionCurso;
		this.estrategia = estrategia;
		// Preguntas
		List<Pregunta> preguntasList = especificacionCurso.getPreguntas();
		mapPreguntas = IntStream.rangeClosed(1, especificacionCurso.getNumPreguntas()).boxed()
				.collect(Collectors.toMap(i -> i, i -> preguntasList.get(i - 1)));
		mapIndexPreguntas = IntStream.range(0, preguntasList.size()).boxed()
				.collect(Collectors.toMap(preguntasList::get, i -> i));
		this.progreso = new Progreso(this);
	}

	public Curso(EspecificacionCurso especificacionCurso, EstrategiaAprendizaje estrategia, Progreso progreso) {
		this(especificacionCurso, estrategia);
		this.progreso = progreso;
	}
	

	public Integer getIndexPregunta(Pregunta pregunta) {
		return mapIndexPreguntas.get(pregunta)+1;
	}

	public boolean responderPregunta(Pregunta pregunta, int respuesta) {
		boolean correcta = pregunta.esCorrecta(respuesta);
		int numPregunta = getIndexPregunta(pregunta);
		updateProgreso(numPregunta+1, correcta);
		estrategia.responderPregunta(mapPreguntas, pregunta, correcta);
		return correcta;
	}

	public Pregunta getSiguientePregunta(Progreso progreso) {
		return estrategia.obtenerSiguientePregunta(mapPreguntas, progreso);
	}

	// Multiple choice
	private void updateProgreso(int lastPregunta, boolean correcta) {
		if (progreso == null) {
			progreso = new Progreso(this);
		}
		progreso.update(lastPregunta, correcta);
	}

	public Progreso getProgreso() {
		return progreso;
	}

	public EstrategiaAprendizaje getEstrategia() {
		return estrategia;
	}

	public EspecificacionCurso getEspecificacionCurso() {
		return especificacionCurso;
	}

	public String getNombre() {
		return especificacionCurso.getNombre();
	}

	public int getNumPreguntas() {
		return mapPreguntas.size();
	}

	public void setEstrategia(EstrategiaAprendizaje e) {
		this.estrategia = e;
	}

	// HashCode y Equals solo tienen en cuenta el curso y la estrategia
	@Override
	public int hashCode() {
		return Objects.hash(especificacionCurso, estrategia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(especificacionCurso, other.especificacionCurso)
				&& Objects.equals(estrategia, other.estrategia);
	}

	public void setProgreso(Progreso progreso) {
		this.progreso = progreso;
	}

	public void resetearCurso() {
		this.progreso.resetear();
	}

	public int getLastPregunta() {
		if (progreso.getLastPregunta() > mapPreguntas.size()) {
			this.resetearCurso();
		}
		return progreso.getLastPregunta();
	}
	
}
