package com.github.dhiraj072.yrandom.video;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

  @RequestMapping(value = "/video/random")
  Video getRandomVideo() {

      return new Video("name");
  }
}
