package umu.pds.dominio;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Curso {
	
	private EstrategiaApredizaje estrategia;
	private EspecificacionCurso especificacionCurso;
	private Progreso progreso;
	private List<Pregunta> preguntas;
	
	public Curso(EspecificacionCurso especificacionCurso) {
		this.especificacionCurso = especificacionCurso;
		// Constructor de copia 
		preguntas = new LinkedList<Pregunta>(especificacionCurso.getPreguntas());
	}
	
	public boolean responderPregunta(Pregunta pregunta, int respuesta, int numPregunta) {
		boolean correcta = pregunta.esCorrecta(respuesta);
		updateProgreso(numPregunta, correcta);
		estrategia.responderPregunta(preguntas, pregunta, correcta);
		return correcta;
    }

	public Pregunta getSiguientePregunta(Progreso progreso) {
		return estrategia.obtenerSiguientePregunta(preguntas, progreso);
	}
	
	// Multiple choice
	private void updateProgreso(int lastPregunta, boolean correcta) {
		if (progreso == null) {
			progreso = new Progreso(0,0,0,this);
		}
		progreso.update(lastPregunta, correcta);
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

	public void setEstrategia(EstrategiaApredizaje e) {
		this.estrategia = e;
	}
	
}
