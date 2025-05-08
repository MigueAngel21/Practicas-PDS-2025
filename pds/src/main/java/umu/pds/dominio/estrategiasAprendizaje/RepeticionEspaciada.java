package umu.pds.dominio.estrategiasAprendizaje;

import java.util.Map;

import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class RepeticionEspaciada implements EstrategiaApredizaje {
	
	String id = "repeticion_espaciada";
	boolean firstTime = true;
	
	private void setUp(Map<Integer, Pregunta> preguntas, Progreso progreso) {
		// Borrar las correctas
		if (progreso != null) {
			progreso.getRespuestasCorrectasList().stream().forEach(index -> {
				preguntas.remove(index);
			});
			// Insertar las incorrectas al final
			progreso.getRespuestasIncorrectasList().stream().forEach(index -> {
				Pregunta pregunta = preguntas.get(index);
				preguntas.remove(index);
				preguntas.put(preguntas.size(), pregunta);
			});
		}
	}

	@Override
	public Pregunta obtenerSiguientePregunta(Map<Integer, Pregunta> preguntas, Progreso progreso) {
		if (firstTime) {
			firstTime = false;
			setUp(preguntas, progreso);
		}

		int last = 1;
		if (progreso != null) {
			last = progreso.getLastPregunta();
		}
		if (last >= preguntas.size()) {
			return null;
		}

		Pregunta pregunta = preguntas.get(last);
		return pregunta;

	}

	

}
