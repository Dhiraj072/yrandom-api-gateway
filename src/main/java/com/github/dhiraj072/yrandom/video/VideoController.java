package com.github.dhiraj072.yrandom.video;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yrandom")
public class VideoController {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(VideoController.class);

  @Autowired
  private YoutubeServiceProxy youtubeServiceProxy;

  /**
   * Get a random video
   * Allows cross-origin requests from staging/uat localhost ports,
   * and from prod app deployed in heroku
   * @return Map containing video information, which jackson is able to
   * serialize out of the box
   */
  @CrossOrigin(origins = { "http://localhost:3000", "http://localhost:5000", "https://yrandom.herokuapp.com" })
  @RequestMapping(value = "/video/random")
  Map<String, Object> getRandomVideo() {

    Map<String, Object> videoJson = youtubeServiceProxy.getRandomVideo();
    LOGGER.info("Got random video {}", videoJson);
    return videoJson;
  }

  @RequestMapping(value = "/test")
  String getTest() {

    return "Hello world changed again!";
  }
}
