package umu.pds.dominio;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({ @JsonSubTypes.Type(value = Flashcard.class, name = "flashcard"),
		@JsonSubTypes.Type(value = MultipleChoice.class, name = "multipleChoice"),
})
public abstract class Pregunta { 
	private String enunciado;
	
	public Pregunta(String enunciado) {
		this.enunciado = enunciado;
	}
	
	public Pregunta() {
	}

	public String getEnunciado() {
		return enunciado;
	}
	
	abstract public boolean esCorrecta(int respuesta);
}
