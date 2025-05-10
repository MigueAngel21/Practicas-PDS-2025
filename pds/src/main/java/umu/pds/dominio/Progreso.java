package umu.pds.dominio;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Progreso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@ElementCollection
	List<Integer> respuestasCorrectas;
	@ElementCollection
	List<Integer> respuestasIncorrectas;
	private int lastPregunta;
	private int correctas;
	private int incorrectas;
	// Necesarios para la conexion entre JSON y JPA
	private String nombreCurso;
	private String estretegia;
	@Transient
	private Curso curso;

	public Progreso(Curso curso) {
		this.respuestasCorrectas = new LinkedList<Integer>();
		this.respuestasIncorrectas = new LinkedList<Integer>();
		this.lastPregunta = 1;
		this.curso = curso;
		this.estretegia = curso.getEstrategia().getClass().getSimpleName();
		this.nombreCurso = curso.getNombre();
	}
	
	// Constructor por defecto para JPA
	public Progreso() {
	}
	
	// Multiple choice
	public void update(int lastPregunta, boolean correcta) {
		this.lastPregunta = lastPregunta;
		if (correcta) {
			respuestasCorrectas.add(lastPregunta);
			correctas++;
        } else {
            respuestasIncorrectas.add(lastPregunta);
            incorrectas++;
		}
	}
	
	public int getRespuestasCorrectas() {
		return correctas;
	}

	public int getRespuestasIncorrectas() {
		return incorrectas;
	}

	public int getLastPregunta() {
		return lastPregunta;
	}

	public Curso getCurso() {
		return curso;
	}
	
	public String toString() {
		return curso.getNombre();
	}
	
	public int getCompletitud() {
		return (lastPregunta) * 100 / curso.getNumPreguntas();
	}
	
	public String getNombreCurso() {
		return nombreCurso;
	}
	
	public String getEstrategia() {
		return estretegia;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isSameCurso(Progreso progreso) {
		return this.estretegia.equals(progreso.getEstrategia()) && this.nombreCurso.equals(progreso.getNombreCurso());
	}

	public void resetear() {
        this.lastPregunta = 1;
        this.respuestasCorrectas.clear();
        this.respuestasIncorrectas.clear();
	}
	
	public List<Integer> getRespuestasCorrectasList() {
		return Collections.unmodifiableList(respuestasCorrectas);
	}
	
	public List<Integer> getRespuestasIncorrectasList() {
		return Collections.unmodifiableList(respuestasIncorrectas);
	}

	
}
