package umu.pds.dominio;

public class MultipleChoice extends Pregunta {

	private int numOpciones;
	private String[] opciones;
	private int correcta;
	private String pregunta;
	private boolean contieneImagen = false;
	
	
	public MultipleChoice(String enunciado, String pregunta, int numOpciones, String[] opciones, int correcta) {
		super(enunciado);
		this.pregunta = pregunta;
		this.numOpciones = numOpciones;
		this.opciones = opciones;
		this.correcta = correcta;
	}
	
	// Constructor with variadic arguments
	public MultipleChoice(String enunciado,String pregunta, int numOpciones, int correcta, String... opciones) {
		super(enunciado);
		this.pregunta = pregunta;
		this.numOpciones = numOpciones;
		this.opciones = opciones;
		this.correcta = correcta;
		for (int i = 0; i < numOpciones; i++) {
			this.opciones[i] = opciones[i];
		}
	}
	
	public MultipleChoice(String enunciado, String pregunta, int numOpciones, int correcta, boolean contieneImagen, String... opciones) {
		this(enunciado, pregunta, numOpciones, correcta, opciones);
		this.contieneImagen = contieneImagen;
	}
	
	public String getPregunta() {
		return pregunta;
	}

	public int getNumOpciones() {
		return numOpciones;
	}

	public String[] getOpciones() {
		return opciones;
	}

	public int getCorrecta() {
		return correcta;
	}
	
	public String getEnunciado() {
		return super.getEnunciado();
	}
	
	public boolean contieneImagen() {
		return contieneImagen;
	}

	public boolean responder(int respuesta) {
		return respuesta == correcta;
	}
	
}
