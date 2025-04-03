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
	
	public boolean responderPregunta(MultipleChoice pregunta, int respuesta, int numPregunta) {
		boolean ret = pregunta.responder(respuesta);
		updateProgreso(numPregunta, ret);
		preguntas.remove(pregunta);
		return ret;
    }

	public void responderPregunta(Flashcard preguntaActual, int numPregunta) {
		updateProgreso(numPregunta);
		preguntas.remove(preguntaActual);
	}


	public Pregunta getSiguientePregunta() {
		if (preguntas.size() > 0) {
			return preguntas.get(0);
		}
		return null;
	}
	
	// Multiple choice
	private void updateProgreso(int lastPregunta, boolean correcta) {
		if (progreso == null) {
			progreso = new Progreso(0,0,0,this);
		}
		progreso.update(lastPregunta, correcta);
	}
	
	// Flashcard
	private void updateProgreso(int lastPregunta) {
		if (progreso == null) {
			progreso = new Progreso(0,0,0,this);
		}
		progreso.update(lastPregunta);
	}
	
	public Progreso getProgreso() {
		return progreso;
	}
	
	public EstrategiaApredizaje getEstrategia() {
		return estrategia;
	}
	
	public EspecificacionCurso getEspecificacionCurso() {
        return especificacionCurso;
    }
	
	public String getNombre() {
		return especificacionCurso.getNombre();
	}
	
	public int getNumPreguntas() {
		return preguntas.size();
	}
	
}
