package com.github.dhiraj072.yrandom.video;

import com.google.api.services.youtube.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

  private final YoutubeManager youtubeManager;

  @Autowired
  public VideoController(YoutubeManager youtubeManager) {

    this.youtubeManager = youtubeManager;
  }

  @RequestMapping(value = "/video/random")
  Video getRandomVideo() {

    return youtubeManager.getRandomYoutubeVideo();
  }
}
