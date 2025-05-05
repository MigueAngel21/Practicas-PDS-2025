package umu.pds.dominio;


import jakarta.persistence.*;

@Entity
public class Progreso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	private int respuestasCorrectas;
	private int respuestasIncorrectas;
	private int lastPregunta;
	@Transient
	private Curso curso;

	public Progreso(int respuestasCorrectas, int respuestasIncorrectas, int lastPregunta, Curso curso) {
        this.respuestasCorrectas = respuestasCorrectas;
        this.respuestasIncorrectas = respuestasIncorrectas;
        this.lastPregunta = lastPregunta;
        this.curso = curso;
    }
	
	public Progreso(Curso curso) {
		this.respuestasCorrectas = 0;
		this.respuestasIncorrectas = 0;
		this.lastPregunta = 0;
		this.curso = curso;
	}
	
	// Multiple choice
	public void update(int lastPregunta, boolean correcta) {
		this.lastPregunta = lastPregunta;
		if (correcta) {
			respuestasCorrectas++;
		} else {
			respuestasIncorrectas++;
		}
	}
	
	// Flashcard
	public void update(int lastPregunta) {
		this.lastPregunta = lastPregunta;
	}

	public int getRespuestasCorrectas() {
		return respuestasCorrectas;
	}

	public int getRespuestasIncorrectas() {
		return respuestasIncorrectas;
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
	

	
}
