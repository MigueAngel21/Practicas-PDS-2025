package umu.pds.dominio.estrategiasAprendizaje;

import java.util.Map;
import java.util.Objects;

import umu.pds.dominio.EstrategiaApredizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class Secuencial implements EstrategiaApredizaje {

	// add ID
	String id = "secuencial";

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
		Secuencial other = (Secuencial) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Pregunta obtenerSiguientePregunta(Map<Integer, Pregunta> preguntas, Progreso progreso) {

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
