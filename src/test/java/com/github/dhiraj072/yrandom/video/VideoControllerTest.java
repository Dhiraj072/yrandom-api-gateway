package com.github.dhiraj072.yrandom.video;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.google.api.services.youtube.model.Video;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class VideoControllerTest {

  private YoutubeManager youtubeManager;
  private VideoController controller;

  @Before
  public void setUp() {

    youtubeManager = Mockito.mock(YoutubeManager.class);
    controller = new VideoController(youtubeManager);
  }

  @Test
  public void testGetRandomVideo() {

    Video randomVideo = new Video();
    randomVideo.setId("randomId");
    when(youtubeManager.getRandomYoutubeVideo()).thenReturn(randomVideo);
    assertEquals("randomId", controller.getRandomVideo().getId());
  }
}