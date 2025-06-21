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
import umu.pds.dominio.estrategiasAprendizaje.Multijugador;

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
	private String estrategia;
	private boolean turnoA;
	private int puntosA;
	private int puntosB;
	@Transient
	private Curso curso;
	private int puntosFinalA;
	private int puntosFinalB;
	

	public Progreso(Curso curso) {
		this.respuestasCorrectas = new LinkedList<Integer>();
		this.respuestasIncorrectas = new LinkedList<Integer>();
		this.lastPregunta = 1;
		this.curso = curso;
		this.estrategia = curso.getEstrategia().getClass().getSimpleName();
		System.out.println("Progreso creado con estrategia: " + this.estrategia);
		this.nombreCurso = curso.getNombre();
		this.turnoA = true;
		this.puntosA = 0;
		this.puntosB = 0;
		
	}

	// Constructor por defecto para JPA
	public Progreso() {
	}


	// Multiple choice
	public void update(int lastPregunta, boolean correcta) {
		// lastPregunta es la pregunta por la que nos hemos quedado, lastPregunta-1 es la pregunta que hemos respondido
		this.lastPregunta = lastPregunta;
		if (correcta) {
			respuestasCorrectas.add(lastPregunta-1);
			correctas++;
			if (turnoA) {
				puntosA++;
			} else {
				puntosB++;
			}
		} else {
			respuestasIncorrectas.add(lastPregunta-1);
			incorrectas++;
		}
		turnoA = !turnoA; // Cambiamos el turno
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
		return ((correctas + incorrectas) * 100 / curso.getNumPreguntas());
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public String getEstrategia() {
		return estrategia;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public boolean isSameCurso(Progreso progreso) {
		return this.estrategia.equals(progreso.getEstrategia()) && this.nombreCurso.equals(progreso.getNombreCurso());
	}

	public void resetear() {
		this.lastPregunta = 1;
		this.respuestasCorrectas.clear();
		this.respuestasIncorrectas.clear();
		this.correctas = 0;
		this.incorrectas = 0;
		this.turnoA = true;
		this.puntosFinalA = this.puntosA;
		this.puntosFinalB = this.puntosB;
		this.puntosA = 0;
		this.puntosB = 0;
	}

	public List<Integer> getRespuestasCorrectasList() {
		return Collections.unmodifiableList(respuestasCorrectas);
	}

	public List<Integer> getRespuestasIncorrectasList() {
		return Collections.unmodifiableList(respuestasIncorrectas);
	}
	
	public boolean isTurnoA() {
		return turnoA;
	}
	
	public void setTurnoA(boolean turnoA) {
		this.turnoA = turnoA;
	}
	
	public int getPuntosA() {
		return puntosA;
	}
	
	public int getPuntosB() {
		return puntosB;
	}
	
	public int getPuntosFinalA() {
		return puntosFinalA;
	}
	
	public int getPuntosFinalB() {
		return puntosFinalB;
	}

}
