package com.github.dhiraj072.yrandom.video;

import static org.junit.Assert.*;

import org.junit.Test;

public class VideoControllerTest {

  VideoController controller = new VideoController();

  @Test
  public void testGetRandomVideo() {

    assertEquals("name", controller.getRandomVideo().get(0).getName());
  }
}