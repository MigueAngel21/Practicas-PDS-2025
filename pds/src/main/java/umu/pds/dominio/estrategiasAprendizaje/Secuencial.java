package umu.pds.dominio.estrategiasAprendizaje;

import java.util.List;

import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class Secuencial implements EstrategiaApredizaje {

	@Override
	public Pregunta obtenerSiguientePregunta(List<Pregunta> preguntas, Progreso progreso) {

		// Seleccionar la siguiente pregunta en la lista
		System.out.println("Progreso: " + progreso);
		int last = 0;
		if (progreso != null) {
			last = progreso.getLastPregunta();
		}
		Pregunta pregunta = preguntas.get(last);
		return pregunta;
	}

	@Override
	public void responderPregunta(List<Pregunta> preguntas, Pregunta pregunta, boolean correcta) {
		preguntas.remove(pregunta);
	}

}
