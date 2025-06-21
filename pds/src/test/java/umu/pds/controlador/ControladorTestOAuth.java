package umu.pds.controlador;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import umu.pds.dominio.OAuthProvider;
import umu.pds.dominio.RepositorioUsuarios;
import umu.pds.dominio.Usuario;

class ControladorTestOAuth {

	private OAuthProvider provider = Mockito.mock(OAuthProvider.class);
	private RepositorioUsuarios repositorioUsuarios = Mockito.mock(RepositorioUsuarios.class);
	private Usuario usuario;
	private Controlador controlador;

	@Test
	public void testThirdPartyIniciarSesionNewUserSuccess() throws Exception {
		// Arrange
		String fakeToken = "fake-token";
		List<String> fakeUserInfo = Arrays.asList("Bob", "bob@gmail.com", "google-123");

		when(provider.getAccessToken()).thenReturn(fakeToken);
		when(provider.getUserInfo(fakeToken)).thenReturn(fakeUserInfo);
		when(repositorioUsuarios.getUsuario("bob@gmail.com")).thenReturn(null);

		controlador = new Controlador(repositorioUsuarios, provider);
		// Simular creacion de usuario
		doAnswer(invocation -> {
			Usuario user = invocation.getArgument(0); // aqui se captura el usuario (el any() de abajo)
			when(repositorioUsuarios.getUsuario("bob@gmail.com")).thenReturn(user); // se devuelve dicho usuario
			return null;
		}).when(repositorioUsuarios).add(any());

		// Act
		boolean result = controlador.thirdPartyIniciarSesion();

		// Assert
		assertTrue(result);
	}

	@Test
	public void testThirdPartyIniciarSesionExistingUserSuccess() throws Exception {
		// Arrange
		String fakeToken = "fake-token";
		char[] fakeTokenChar = { 'g', 'o', 'o', 'g', 'l', 'e', '-', '7', '8', '9' };
		List<String> fakeUserInfo = Arrays.asList("Alice", "alice@example.com", "google-789");

		when(provider.getAccessToken()).thenReturn(fakeToken);
		when(provider.getUserInfo(fakeToken)).thenReturn(fakeUserInfo);
		controlador = new Controlador(repositorioUsuarios, provider);
		controlador.registrarUsuario("Alice", "alice@example.com", fakeTokenChar, "18");

		// Act
		boolean result = controlador.thirdPartyIniciarSesion();

		// Assert
		assertTrue(result);
	}

	@Test
	public void testThirdPartyIniciarSesionTokenFailureReturnsFalse() throws Exception {
		// Simulate failure to get token
		when(provider.getAccessToken()).thenThrow(new RuntimeException("No token"));
		
		controlador = new Controlador(repositorioUsuarios, provider);

		boolean result = controlador.thirdPartyIniciarSesion();

		assertFalse(result);
	}

	@Test
	public void testThirdPartyIniciarSesionUserInfoFailureReturnsFalse() throws Exception {
		when(provider.getAccessToken()).thenReturn("ok");
		when(provider.getUserInfo("ok")).thenThrow(new RuntimeException("No info"));
		controlador = new Controlador(repositorioUsuarios, provider);

		boolean result = controlador.thirdPartyIniciarSesion();

		assertFalse(result);
	}
}
