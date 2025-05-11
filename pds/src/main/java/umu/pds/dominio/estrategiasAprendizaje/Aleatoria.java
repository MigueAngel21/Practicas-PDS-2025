package umu.pds.dominio.estrategiasAprendizaje;

import java.util.List;

import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class Aleatoria implements EstrategiaApredizaje {

	@Override
	public Pregunta obtenerSiguientePregunta(List<Pregunta> preguntas, Progreso progreso) {
		// Seleccionar una pregunta aleatoria
		if (preguntas.size() > 0) {
            int randomIndex = (int) (Math.random() * preguntas.size());
            Pregunta pregunta = preguntas.get(randomIndex);
            return pregunta;
        }
        return null;
	}

	@Override
	public void responderPregunta(List<Pregunta> preguntas, Pregunta pregunta, boolean correcta) {
		preguntas.remove(pregunta);
	}

}
