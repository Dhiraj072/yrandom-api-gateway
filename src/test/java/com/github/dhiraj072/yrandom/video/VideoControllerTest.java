package com.github.dhiraj072.yrandom.video;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
@TestInstance(Lifecycle.PER_CLASS)
class VideoControllerTest {

  @Spy
  private YoutubeServiceProxy youtubeServiceProxy;

  @InjectMocks
  private VideoController controller;

  @BeforeAll
  void setUp() {

    MockitoAnnotations.initMocks(this);
    Map<String, Object> videoJson = new HashMap<>();
    videoJson.put("id", "1");
    Mockito.when(youtubeServiceProxy.getRandomVideo()).thenReturn(videoJson);
  }

  @Test
  void testGetRandomVideo() {

    Map<String, Object> result = controller.getRandomVideo();
    assertNotNull(result);
    assertEquals("1", result.get("id"));
  }
}