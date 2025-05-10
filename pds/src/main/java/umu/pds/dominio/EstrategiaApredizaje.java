package umu.pds.dominio;

import java.util.Map;

public interface EstrategiaApredizaje {
    Pregunta obtenerSiguientePregunta(Map<Integer, Pregunta> preguntas, Progreso progreso);

	void responderPregunta(Map<Integer, Pregunta> preguntas, Pregunta pregunta, boolean correcta);
    
};