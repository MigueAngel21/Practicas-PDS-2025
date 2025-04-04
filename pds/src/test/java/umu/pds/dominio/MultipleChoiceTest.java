package umu.pds.dominio;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MultipleChoiceTest {

    private MultipleChoice pregunta;
    private MultipleChoice preguntaConImagen;

    @BeforeEach
    public void setUp() {
        pregunta = new MultipleChoice("Pregunta 1", "¿Cuántas champions league tiene el real madrid?", 3, 1, "15", "14", "13");
        preguntaConImagen = new MultipleChoice("Pregunta 2", "¿Cuál es el escudo del Real Madrid?", 3, 2, true, "/path/escudo1.png", "/path/escudo2.png", "/path/escudo3.png");
    }

    @Test
    public void testConstructorSinImagen() {
        assertEquals("Pregunta 1", pregunta.getEnunciado());
        assertEquals("¿Cuántas champions league tiene el real madrid?", pregunta.getPregunta());
        assertEquals(3, pregunta.getNumOpciones());
        assertArrayEquals(new String[]{"15", "14", "13"}, pregunta.getOpciones());
        assertEquals(1, pregunta.getCorrecta());
        assertFalse(pregunta.contieneImagen());
    }

    @Test
    public void testConstructorConImagen() {
        assertEquals("Pregunta 2", preguntaConImagen.getEnunciado());
        assertEquals("¿Cuál es el escudo del Real Madrid?", preguntaConImagen.getPregunta());
        assertEquals(3, preguntaConImagen.getNumOpciones());
        assertArrayEquals(new String[]{"/path/escudo1.png", "/path/escudo2.png", "/path/escudo3.png"}, preguntaConImagen.getOpciones());
        assertEquals(2, preguntaConImagen.getCorrecta());
        assertTrue(preguntaConImagen.contieneImagen());
    }

    @Test
    public void testResponderCorrecto() {
        assertTrue(pregunta.responder(1));
    }

    @Test
    public void testResponderIncorrecto() {
        assertFalse(pregunta.responder(0));
        assertFalse(pregunta.responder(2));
    }
}
