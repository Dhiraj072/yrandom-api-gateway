package com.github.dhiraj072.yrandom.video;

import com.google.api.services.youtube.model.Video;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yrandom")
public class VideoController {

  @RequestMapping(value = "/video/random")
  Video getRandomVideo() {

    return new Video();
  }
}
