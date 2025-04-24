package umu.pds.dominio.estrategiasAprendizaje;

import java.util.List;

import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.Pregunta;

public class Secuencial implements EstrategiaApredizaje {

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
		preguntas.remove(pregunta);
	}
	
	

}
