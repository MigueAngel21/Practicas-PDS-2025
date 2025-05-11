package umu.pds.dominio.estrategiasAprendizaje;

import java.util.Map;

import umu.pds.dominio.EstrategiaAprendizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class RepeticionEspaciada implements EstrategiaAprendizaje {
	
	String id = "repeticion_espaciada";
	boolean firstTime = true;
	Map<Integer, Pregunta> preguntasBckup;
	
	private void setUp(Map<Integer, Pregunta> preguntas, Progreso progreso) {
		// Borrar las correctas
		preguntasBckup = new java.util.HashMap<>(preguntas);
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
		if (preguntas.size() == 0) {
			preguntasBckup.forEach((index, pregunta) -> {
				preguntas.put(index, pregunta);
			});
			firstTime = true;
			return null;
		}

		Pregunta pregunta = preguntas.get(last);
		return pregunta;

	}

	
	@Override
	public void responderPregunta(Map<Integer, Pregunta> preguntas, Pregunta pregunta, boolean correcta) {
		if(!correcta) {
			preguntas.put(preguntas.size()+1, pregunta);
		}
        // remove the question from the map (first occurrence only)
		for (Map.Entry<Integer, Pregunta> entry : preguntas.entrySet()) {
			if (entry.getValue().equals(pregunta)) {
				preguntas.remove(entry.getKey());
				break;
			}
		}
	}

}
