package umu.pds.dominio;

public class Progreso {
	
	private int respuestasCorrectas;
	private int respuestasIncorrectas;
	private int lastPregunta;
	private Curso curso;

	public Progreso(int respuestasCorrectas, int respuestasIncorrectas, Curso curso) {
        this.respuestasCorrectas = respuestasCorrectas;
        this.respuestasIncorrectas = respuestasIncorrectas;
        this.curso = curso;
    }
	
	public void update(int lastPregunta, boolean correcta) {
		this.lastPregunta = lastPregunta;
		if (correcta) {
			respuestasCorrectas++;
		} else {
			respuestasIncorrectas++;
		}
	}
	
	

	
}
