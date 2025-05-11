package umu.pds.dominio;

import java.util.List;

public interface EstrategiaApredizaje {
    Pregunta obtenerSiguientePregunta(List<Pregunta> preguntas, Progreso progreso);
    
    void responderPregunta(List<Pregunta> preguntas, Pregunta pregunta, boolean correcta);
};