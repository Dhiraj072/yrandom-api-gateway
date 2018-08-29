package com.github.dhiraj072.yrandom.video;

import static org.junit.Assert.*;

import org.junit.Test;

public class VideoControllerTest {

  private VideoController controller = new VideoController();

  @Test
  public void testGetRandomVideo() {

    assertNotNull(controller.getRandomVideo());
  }
}