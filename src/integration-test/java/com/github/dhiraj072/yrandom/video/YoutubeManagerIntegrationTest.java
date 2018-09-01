package com.github.dhiraj072.yrandom.video;

import static org.junit.Assert.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class YoutubeManagerIntegrationTest {

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