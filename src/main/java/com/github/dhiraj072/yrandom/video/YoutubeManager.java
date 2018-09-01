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
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
class YoutubeManager {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(YoutubeManager.class);

  /**
   * Application name.
   */
  private static final String APPLICATION_NAME = "yrandom";

  /**
   * Directory to store user credentials for this application.
   */
  private static final java.io.File DATA_STORE_DIR = new java.io.File(
      System.getProperty("user.home"), ".credentials/java-youtube-api-tests");

  private static FileDataStoreFactory DATA_STORE_FACTORY;

  private static final JsonFactory JSON_FACTORY = JacksonFactory
      .getDefaultInstance();

  private static HttpTransport HTTP_TRANSPORT;

  // Youtube API client service
  private static YouTube youtube;

  /**
   * Global instance of the scopes required by this quickstart.
   *
   * If modifying these scopes, delete your previously saved credentials at
   * ~/.credentials/drive-java-quickstart
   */
  private static final Collection<String> SCOPES = Collections.singletonList(
      "https://www.googleapis.com/auth/youtube.force-ssl https://www.googleapis.com/auth/youtubepartner");

  YoutubeManager() throws IOException, GeneralSecurityException {

    HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
    youtube = getYouTubeService();
  }

  /**
   * Creates an authorized Credential object.
   *
   * @return an authorized Credential object.
   */
  private static Credential authorize() throws IOException {

    // Load client secrets.
    InputStream in = YoutubeManager.class
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

  Video getRandomYoutubeVideo() {

    String videoId = "";
    try {

      YouTube.Search.List searchListByKeywordRequest = youtube.search()
          .list("snippet");
      searchListByKeywordRequest.setMaxResults((long) 1);
      searchListByKeywordRequest.setQ("surfing");
      searchListByKeywordRequest.setType("video");

      SearchListResponse response = searchListByKeywordRequest.execute();
      videoId = response.getItems().get(0).getId().getVideoId();
      LOGGER.debug("First video id {}", videoId);
      LOGGER.debug("Full response {}", response);
    } catch (GoogleJsonResponseException e) {

      e.printStackTrace();
      LOGGER.error("There was a service error: {} : {}",
          e.getDetails().getCode(), e.getDetails().getMessage());
    } catch (Throwable t) {

      t.printStackTrace();
    }

    return getVideoById(videoId);
  }

  private Video getVideoById(String videoId) {

    Video video = null;
    try {

      YouTube.Videos.List videosListByIdRequest =
          youtube.videos().list("snippet");
      videosListByIdRequest.setId(videoId);
      VideoListResponse response = videosListByIdRequest.execute();
      video = response.getItems().get(0);
      LOGGER.info("VideoListResponse {}", response);
    } catch (IOException e) {

      e.printStackTrace();
    }
    return video;
  }
}