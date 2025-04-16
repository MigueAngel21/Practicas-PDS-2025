package umu.pds.dominio;

import java.util.Collections;
import java.util.List;

public class EspecificacionCurso {

	private String nombre;
	private String descripcion;
	private List<Pregunta> preguntas;
	
	public EspecificacionCurso(String nombre, String descripcion, List<Pregunta> preguntas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.preguntas = preguntas;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public List<Pregunta> getPreguntas() {
		return Collections.unmodifiableList(preguntas);
	}
	
}
