package umu.pds.dominio;

import java.util.List;

// Patrón Adaptador para la autenticación OAuth Third Party
public interface OAuthProvider {
    /**
     * Opens the login URL in the user's browser.
     */
    void authenticate();

    String getAccessToken() throws Exception;

    /**
     * Utilizar el token de acceso para conseguir una lista con el nombre, email y provider_id
     */
    List<String> getUserInfo(String accessToken) throws Exception;
}
