package umu.pds.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuarioTest {

    private Usuario usuario;
    private Estadistica estadistica;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario("Paco", "paco@duopingo.com", "1234", 18);
        estadistica = new Estadistica(10, 5);
    }

    @Test
    public void testConstructor() {
        assertEquals("Paco", usuario.getNombre());
        assertEquals("paco@duopingo.com", usuario.getEmail());
        assertEquals("1234", usuario.getPassword());
        assertEquals(18, usuario.getEdad());
        assertNotNull(usuario.getEstadisticas());
        assertNull(usuario.getCursos());
    }

    @Test
    public void testUpdateEstadisticas() {
        usuario.updateEstadisticas(estadistica);
        assertEquals(estadistica, usuario.getEstadisticas());
    }

    @Test
    public void testComprobarContrasena() {
        assertFalse(usuario.comprobarContrasena("1234")); // Método aún no implementado
        assertFalse(usuario.comprobarContrasena("incorrecta"));
    }
}
