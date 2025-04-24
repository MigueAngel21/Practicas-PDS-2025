package umu.pds.dominio.estrategiasAprendizaje;

import java.util.List;

import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.Pregunta;

public class RepeticionEspaciada implements EstrategiaApredizaje {

	@Override
	public Pregunta obtenerSiguientePregunta(List<Pregunta> preguntas) {
		if (preguntas.size() > 0) {
            Pregunta pregunta = preguntas.get(0);
            return pregunta;
        }
        return null;
	}

	@Override
	public void responderPregunta(List<Pregunta> preguntas, Pregunta pregunta, boolean correcta) {
		if (!correcta) {
			// Volver a agregar la pregunta al final de la lista
			preguntas.add(pregunta);
		}
		preguntas.remove(pregunta);
	}
	
	

}
