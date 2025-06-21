package umu.pds.dominio.estrategiasAprendizaje;

import java.util.Map;
import java.util.Objects;

import umu.pds.dominio.EstrategiaAprendizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class RepeticionEspaciada implements EstrategiaAprendizaje {
	

	static final String id = "repeticion_espaciada";
	int count = 1;
	boolean firstTime = true;
	Map<Integer, Pregunta> preguntasBckup;
	
	private void setUp(Map<Integer, Pregunta> preguntas, Progreso progreso) {
		// Borrar las correctas
		preguntasBckup = new java.util.HashMap<>(preguntas);
		if (progreso != null) {
			progreso.getRespuestasCorrectasList().stream().forEach(index -> {
				preguntas.remove(index);
			});
			 for (int i = 0; i < progreso.getRespuestasIncorrectasList().size(); i++) {
			 	Pregunta pregunta = preguntas.get(progreso.getRespuestasIncorrectasList().get(i));
			 	preguntas.remove(progreso.getRespuestasIncorrectasList().get(i));
			 	preguntas.put(preguntasBckup.size()+(i+1), pregunta);
			 }
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
			preguntas.put(preguntasBckup.size()+count, pregunta);
			count++;
		}
		 //  Borramos la primera ocurrencia ya que no es buena idea tener dos values con la misma key
		for (Map.Entry<Integer, Pregunta> entry : preguntas.entrySet()) {
			if (entry.getValue().equals(pregunta)) {
				preguntas.remove(entry.getKey());
				break;
			}
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepeticionEspaciada other = (RepeticionEspaciada) obj;
		return Objects.equals(id, other.id);
	}


}
