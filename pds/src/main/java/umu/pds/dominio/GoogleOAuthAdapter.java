package umu.pds.dominio;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfo;

public class GoogleOAuthAdapter implements OAuthProvider {

    private static final String CLIENT_SECRET_FILE = "/client_secret.json"; // Ensure this file is in your resources
    private static final List<String> SCOPES = Arrays.asList(
            "https://www.googleapis.com/auth/userinfo.profile",
            "https://www.googleapis.com/auth/userinfo.email"
    );
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static NetHttpTransport httpTransport;
    private Credential credential;

    @Override
    public void authenticate() {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();

            // Load client secrets
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                    new InputStreamReader(getClass().getResourceAsStream(CLIENT_SECRET_FILE)));

            // Build OAuth 2.0 flow
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                    .setAccessType("offline")
                    .build();

            // Start local web server for authentication
            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

            // Authorize user and store credential
            credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAccessToken() {
        // The Google flow handles everything internally, so we just return the stored access token
        if (credential != null) {
            return credential.getAccessToken();
        } else {
            throw new IllegalStateException("Authentication not yet performed.");
        }
    }
    

    @Override
    public List<String> getUserInfo(String accessToken) throws Exception {
        if (credential == null) {
            throw new IllegalStateException("Authentication not yet performed.");
        }

        // Use credential to fetch user info
        Oauth2 oauth2 = new Oauth2.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName("Duopingo")
                .build();

        Userinfo userInfo = oauth2.userinfo().get().execute();
        // Meter en una lista nombre, email y provider_id
		List<String> userInfoList = Arrays.asList(userInfo.getName(), userInfo.getEmail(), userInfo.getId());
        return userInfoList;
    }
}

