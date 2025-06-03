package umu.pds.dominio.estrategiasAprendizaje;

import java.util.Map;
import java.util.Objects;
import umu.pds.dominio.EstrategiaAprendizaje;
import umu.pds.dominio.Pregunta;
import umu.pds.dominio.Progreso;

public class Multijugador implements EstrategiaAprendizaje {
	//hace que se tenga que responder a las preguntas del curso de forma secuencial como en la estrategia secuencial
	//pero va alternando entre jugador 1 y jugador 2
	static final String id = "multijugador";
	@Override
	public int hashCode() {
        return id.hashCode();
    }	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Multijugador that = (Multijugador) obj;
		return id.equals(that.id);
	}
	@Override
	public String toString() {
		return "Multijugador{" + "id='" + id + '\'' + '}';
	}
	// Aquí podrías implementar la lógica específica para la estrategia de multijugador
	public String getId() {
		return id;
	}
	
	public Pregunta obtenerSiguientePregunta(Map<Integer, Pregunta> preguntas, Progreso progreso) {
		int last = 1;
		if (progreso != null) {
			last = progreso.getLastPregunta();
		}
		if (last > preguntas.size()) {
			return null;
		}
		Pregunta pregunta = preguntas.get(last);
		return pregunta;
	}
	
	public void responderPregunta(Map<Integer, Pregunta> preguntas, Pregunta pregunta, boolean correcta) {
		return;
	}
	
	
}
