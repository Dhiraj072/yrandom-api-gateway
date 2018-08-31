package com.github.dhiraj072.yrandom.video;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VideoSearch {

  private static final Logger LOGGER = LoggerFactory.getLogger(VideoSearch.class);

  /**
   * Application name.
   */
  private static final String APPLICATION_NAME = "yrandom";

  /**
   * Directory to store user credentials for this application.
   */
  private static final java.io.File DATA_STORE_DIR = new java.io.File(
      System.getProperty("user.home"), ".credentials/java-youtube-api-tests");

  /**
   * Global instance of the {@link FileDataStoreFactory}.
   */
  private static FileDataStoreFactory DATA_STORE_FACTORY;

  /**
   * Global instance of the JSON factory.
   */
  private static final JsonFactory JSON_FACTORY = JacksonFactory
      .getDefaultInstance();

  /**
   * Global instance of the HTTP transport.
   */
  private static HttpTransport HTTP_TRANSPORT;

  /**
   * Global instance of the scopes required by this quickstart.
   *
   *
   * If modifying these scopes, delete your previously saved credentials at
   * ~/.credentials/drive-java-quickstart
   */
  private static final Collection<String> SCOPES = Collections.singletonList(
      "https://www.googleapis.com/auth/youtube.force-ssl https://www.googleapis.com/auth/youtubepartner");

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
  private static Credential authorize() throws IOException {

    // Load client secrets.
    InputStream in = VideoSearch.class
        .getResourceAsStream("/client_secret.json");
    GoogleClientSecrets clientSecrets = GoogleClientSecrets
        .load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        .setDataStoreFactory(DATA_STORE_FACTORY)
        .setAccessType("offline")
        .build();
    Credential credential = new AuthorizationCodeInstalledApp(
        flow, new LocalServerReceiver()).authorize("user");
    LOGGER.debug("Credentials saved to {}", DATA_STORE_DIR.getAbsolutePath());
    return credential;
  }

  /**
   * Build and return an authorized API client service, such as a YouTube Data
   * API client service.
   *
   * @return an authorized API client service
   */
  private static YouTube getYouTubeService() throws IOException {

    Credential credential = authorize();
    return new YouTube.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, credential)
        .setApplicationName(APPLICATION_NAME)
        .build();
  }

  public static void main(String[] args) throws IOException {

    YouTube youtube = getYouTubeService();

    try {

      HashMap<String, String> parameters = new HashMap<>();
      parameters.put("part", "snippet");
      parameters.put("maxResults", "25");
      parameters.put("q", "surfing");
      parameters.put("type", "");

      YouTube.Search.List searchListByKeywordRequest = youtube.search()
          .list(parameters.get("part"));
      if (parameters.containsKey("maxResults")) {

        searchListByKeywordRequest.setMaxResults(
            Long.parseLong(parameters.get("maxResults")));
      }

      if (parameters.containsKey("q") && !parameters.get("q").equals("")) {

        searchListByKeywordRequest.setQ(parameters.get("q"));
      }

      if (parameters.containsKey("type") && !parameters.get("type").equals("")) {

        searchListByKeywordRequest.setType(parameters.get("type"));
      }

      SearchListResponse response = searchListByKeywordRequest.execute();
      LOGGER.debug("First video id {}", response.getItems().get(0).getId().getVideoId());
      LOGGER.debug("Full response {}", response);


    } catch (GoogleJsonResponseException e) {

      e.printStackTrace();
      LOGGER.error("There was a service error: {} : {}",
          e.getDetails().getCode(), e.getDetails().getMessage());
    } catch (Throwable t) {

      t.printStackTrace();
    }
  }
}