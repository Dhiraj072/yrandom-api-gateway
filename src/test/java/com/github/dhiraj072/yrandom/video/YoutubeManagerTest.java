package com.github.dhiraj072.yrandom.video;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.api.services.youtube.YouTube;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.junit.Before;
import org.junit.Test;

public class YoutubeManagerTest {

  private YoutubeManager youtubeManager;

  @Before
  public void init() throws IOException, GeneralSecurityException {

    youtubeManager = new YoutubeManager();
  }

  @Test
  public void testReturnAGoodRandomVideo() {

    assertNotNull(youtubeManager.getRandomYoutubeVideo());
  }

}