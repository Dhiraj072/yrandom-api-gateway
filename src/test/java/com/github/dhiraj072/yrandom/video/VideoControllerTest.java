package com.github.dhiraj072.yrandom.video;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class VideoControllerTest {

  private VideoController controller;

  @BeforeAll
  void setUp() {

    controller = new VideoController();
  }

  @Test
  void testGetRandomVideo() {

    assertNotNull(controller.getRandomVideo());
  }
}