package umu.pds.dominio.estrategiasAprendizaje;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import umu.pds.dominio.EstrategiaAprendizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class Aleatoria implements EstrategiaAprendizaje {

	String id = "aleatoria";
	boolean firstTime = true;
	Map<Integer, Pregunta> preguntasBckup;
	
	private void setUp(Map<Integer, Pregunta> preguntas, Progreso progreso) {
		preguntasBckup = new java.util.HashMap<>(preguntas);
		if (progreso != null) {
			progreso.getRespuestasCorrectasList().stream().forEach(index -> {
				preguntas.remove(index);
			});
			progreso.getRespuestasIncorrectasList().stream().forEach(index -> {
				preguntas.remove(index);
			});
		}
	}
	
	@Override
	public Pregunta obtenerSiguientePregunta(Map<Integer, Pregunta> preguntas, Progreso progreso) {
		if (firstTime) {
			firstTime = false;
			setUp(preguntas,progreso);
		}
		// Seleccionar una pregunta aleatoria
		if (preguntas.size() > 0) {
			// Obtener un índice aleatorio del 1 al tamaño de la lista (exclusivo)
			List<Integer> keys = new ArrayList<>(preguntas.keySet());
			int randomIndex = (int) (Math.random() * preguntas.size());
			Integer randomKey = keys.get(randomIndex);

            Pregunta pregunta = preguntas.get(randomKey);
            // Eliminar la pregunta seleccionada de la lista
            preguntas.remove(randomKey);
            return pregunta;
        }
		// Si no hay preguntas disponibles, devolver null y recargar las preguntas
		// preguntasBckup.forEach((index, pregunta) -> { preguntas.put(index, pregunta); });
        return null;
	}
	
	@Override
	public void responderPregunta(Map<Integer, Pregunta> preguntas, Pregunta pregunta, boolean correcta) {
		return;
	}


}
