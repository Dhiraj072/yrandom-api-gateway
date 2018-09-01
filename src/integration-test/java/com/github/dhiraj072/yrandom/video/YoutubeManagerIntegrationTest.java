package com.github.dhiraj072.yrandom.video;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class YoutubeManagerIntegrationTest {

  @Autowired
  YoutubeManager youtubeManager;

  @Test
  public void testReturnAGoodRandomVideo() {

    assertNotNull(youtubeManager.getRandomYoutubeVideo());
    assertNotNull(youtubeManager.getRandomYoutubeVideo().getId());
  }

}