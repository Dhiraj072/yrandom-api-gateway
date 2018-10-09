package com.github.dhiraj072.yrandom.video;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yrandom")
public class VideoController {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(VideoController.class);

  @Autowired
  YoutubeServiceProxy youtubeServiceProxy;

  /**
   * Get a random video
   * @return Map containing video information, which jackson is able to
   * serialize to json out of the box
   */
  @RequestMapping(value = "/video/random")
  Map<String, Object> getRandomVideo() {

    Map<String, Object> videoJson = youtubeServiceProxy.getRandomVideo();
    LOGGER.info("Got random video {}", videoJson);
    return videoJson;
  }
}
