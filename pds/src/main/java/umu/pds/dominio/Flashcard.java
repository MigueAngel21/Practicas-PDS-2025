package umu.pds.dominio;

public class Flashcard extends Pregunta {
	
	
	private String respuesta;

	public Flashcard(String enunciado, String respuesta) {
		super(enunciado);
		this.respuesta = respuesta;
	}
	
	public Flashcard() {
		super();
		this.respuesta = "";
	}
	
	public String getRespuesta() {
		return respuesta;
	}

	@Override
	public boolean esCorrecta(int respuesta) {
		return respuesta == 1;
	}
	
}
