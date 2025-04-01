package umu.pds.dominio;

import java.util.List;

public class Curso {
	
	private EstrategiaApredizaje estrategia;
	private EspecificacionCurso especificacionCurso;
	private Progreso progreso = null;
	private List<Pregunta> preguntas;
	
	public Curso(EspecificacionCurso especificacionCurso) {
		this.especificacionCurso = especificacionCurso;
		preguntas = especificacionCurso.getPreguntas();
	}
	
	public void responderPregunta(Pregunta pregunta, int respuesta) {
		preguntas.remove(pregunta);
    }

	public Pregunta getSiguientePregunta() {
		if (preguntas.size() > 0) {
			return preguntas.get(0);
		}
		return null;
	}
	
	public Progreso getProgreso() {
		return progreso;
	}
	
}
