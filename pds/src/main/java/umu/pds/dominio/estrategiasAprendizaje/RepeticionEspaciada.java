package umu.pds.dominio.estrategiasAprendizaje;

import java.util.Map;

import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class RepeticionEspaciada implements EstrategiaApredizaje {

	@Override
	public Pregunta obtenerSiguientePregunta(Map<Integer, Pregunta> preguntas, Progreso progreso) {
		if (preguntas.size() > 0) {
            Pregunta pregunta = preguntas.get(0);
            return pregunta;
        }
        return null;
	}

	

}
