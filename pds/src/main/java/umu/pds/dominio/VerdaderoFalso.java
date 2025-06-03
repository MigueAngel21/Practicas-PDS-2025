package umu.pds.dominio;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerdaderoFalso extends Pregunta {
	
	private String pregunta;
	private int correcta;
	@JsonProperty("contieneImagen")
	private boolean contieneImagen = false;
	private List<String> opciones; //lista de imagenes
	private int numOpciones=0; //corresponde a las imagenes que recibe
	
	public VerdaderoFalso(String enunciado, String pregunta, int numOpciones, int correcta, String... opciones) {
		super(enunciado);
		this.pregunta = pregunta;
		this.correcta = correcta;
		if (numOpciones>=1) this.contieneImagen = true;
		this.opciones = new ArrayList<String>();
		for (int i = 0; i < numOpciones; i++) {
			this.opciones.add(opciones[i]);
		}
	}
	
	
	
	public List<String> getOpciones() {
        return opciones;
    }
	
	public int getNumOpciones() {
		return numOpciones;
	}

	public VerdaderoFalso() {
		super();
	}
	
	public String getPregunta() {
		return pregunta;
	}
	
	
	public int getCorrecta() {
		return correcta;
	}
	
	public String getEnunciado() {
		return super.getEnunciado();
	}
	
	@Override
	public boolean esCorrecta(int respuesta) {
		// Compara la respuesta con la respuesta correcta
		return respuesta == correcta;
	}
	
	public boolean contieneImagen() {
		return contieneImagen;
	}
}
