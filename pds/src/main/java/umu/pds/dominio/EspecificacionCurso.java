package umu.pds.dominio;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EspecificacionCurso {

	@JsonProperty
	private String nombre;
	@JsonProperty
	private String descripcion;
	@JsonProperty
	private List<Pregunta> preguntas;
	@JsonProperty
	private String imagen;
	
	public EspecificacionCurso(String nombre, String descripcion, String imagen, List<Pregunta> preguntas) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.preguntas = preguntas;
		this.imagen = imagen;
	}
	
	public EspecificacionCurso() {

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

	public String getImagen() {
		return imagen;
	}
	
}
