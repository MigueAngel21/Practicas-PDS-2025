package umu.pds.dominio;

import java.util.List;

public interface EstrategiaApredizaje {
    Pregunta obtenerSiguientePregunta(List<Pregunta> preguntas, int indiceActual);
};