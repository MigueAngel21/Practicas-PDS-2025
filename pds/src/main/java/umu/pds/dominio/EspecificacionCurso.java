package umu.pds.dominio;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@JsonIgnore
	public int getNumPreguntas() {
		return preguntas.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, imagen, nombre, preguntas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EspecificacionCurso other = (EspecificacionCurso) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(imagen, other.imagen)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(preguntas, other.preguntas);
	}
	
	
	
}
