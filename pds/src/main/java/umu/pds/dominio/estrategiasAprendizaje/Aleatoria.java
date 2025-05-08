package umu.pds.dominio.estrategiasAprendizaje;

import java.util.List;
import java.util.Map;

import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class Aleatoria implements EstrategiaApredizaje {

	String id = "aleatoria";
	boolean firstTime = true;
	
	private void setUp(Map<Integer, Pregunta> preguntas, Progreso progreso) {
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
            int randomIndex = (int) (Math.random() * preguntas.size());
            Pregunta pregunta = preguntas.get(randomIndex);
            // Eliminar la pregunta seleccionada de la lista
            preguntas.remove(randomIndex);
            return pregunta;
        }
        return null;
	}


}
