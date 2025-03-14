package umu.pds.dominio;

public class Flashcard extends Pregunta {
	
	
	private String respuesta;

	public Flashcard(String enunciado, String respuesta) {
		super(enunciado);
		this.respuesta = respuesta;
	}

}
