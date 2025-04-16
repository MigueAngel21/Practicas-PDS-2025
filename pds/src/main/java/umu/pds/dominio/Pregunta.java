package umu.pds.dominio;

public abstract class Pregunta {

	private String enunciado;
	
	public Pregunta(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getEnunciado() {
		return enunciado;
	}
	
	abstract public boolean esCorrecta(int respuesta);
	
}
