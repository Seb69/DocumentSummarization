package com.kyushu.autosum.servicelayer.convertorservice.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.kyushu.autosum.servicelayer.convertorservice.PowerpointToPDFImpl;
import com.kyushu.autosum.servicelayer.convertorservice.exceptions.AuthorizationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * @author ANDRE
 * @since 25/05/16
 */
public class GoogleDriveAuth {

    private static final Logger LOG = LoggerFactory.getLogger(PowerpointToPDFImpl.class);

    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "Converter API";

    /**
     * Directory to store user credentials for this application.
     */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            "src/main/resources/googleapis/credentials");
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();
    /**
     * Global instance of the scopes required by this quickstart.
     * <p>
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart.json
     */
    private static final List<String> SCOPES =
            Arrays.asList(DriveScopes.DRIVE);
    /**
     * Global instance of the {@link FileDataStoreFactory}.
     */
    private static FileDataStoreFactory DATA_STORE_FACTORY;
    /**
     * Global instance of the HTTP transport.
     */
    private static HttpTransport HTTP_TRANSPORT;

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     *
     * @return an authorized Credential object.
     */
     static Credential authorize() {
        // Load client secrets.
        InputStream in =
                PowerpointToPDFImpl.class.getResourceAsStream("/googleapis/client_secret.json");

        GoogleClientSecrets clientSecrets = getGoogleClientSecrets(in);

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = getAuthorizationFlow(clientSecrets);

        Credential credential = getCredential(flow);
        return credential;
    }

    /**
     * Handle IOException during AuthorizationCodeInstalledApp creation
     * @param flow to create
     * @return the credential
     */
    static Credential getCredential(GoogleAuthorizationCodeFlow flow) {

        Credential credential = null;
        try {
            credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        } catch (IOException e) {
            throw new AuthorizationError("Auth2 ERROR", e);
        }
        return credential;
    }

    /**
     * Handle IOException and throw a runtime exception if fail
     *
     * @param googleClientSecrets to create flow
     * @return the flow created
     */
    static GoogleAuthorizationCodeFlow getAuthorizationFlow(GoogleClientSecrets googleClientSecrets) {

        GoogleAuthorizationCodeFlow googleAuthorizationCodeFlow;
        try {
            googleAuthorizationCodeFlow = new GoogleAuthorizationCodeFlow.Builder(
                    HTTP_TRANSPORT, JSON_FACTORY, googleClientSecrets, SCOPES)
                    .setDataStoreFactory(DATA_STORE_FACTORY)
                    .setAccessType("offline")
                    .build();
        } catch (IOException e) {
            throw new AuthorizationError("Auth2 ERROR", e);
        }
        return googleAuthorizationCodeFlow;
    }

    /**
     * Handle IOException and throw a runtime exception if fail
     *
     * @param in to create the file
     * @return the client secrets
     */
    static GoogleClientSecrets getGoogleClientSecrets(InputStream in) {
        GoogleClientSecrets googleClientSecrets;
        try {
            googleClientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        } catch (IOException e) {
            throw new AuthorizationError("Auth2 ERROR", e);
        }
        return googleClientSecrets;
    }

    /**
     * Build and return an authorized Drive client service.
     *
     * @return an authorized Drive client service
     */
    public static Drive getDriveService() {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

}
