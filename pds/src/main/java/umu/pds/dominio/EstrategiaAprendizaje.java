package umu.pds.dominio;

import java.util.Map;

public interface EstrategiaAprendizaje {
    Pregunta obtenerSiguientePregunta(Map<Integer, Pregunta> preguntas, Progreso progreso);

	void responderPregunta(Map<Integer, Pregunta> preguntas, Pregunta pregunta, boolean correcta);
    
};