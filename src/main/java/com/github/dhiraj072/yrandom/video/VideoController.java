package com.github.dhiraj072.yrandom.video;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoController {

  @RequestMapping(value = "/video/random")
  List<Video> getRandomVideo() {

      return Arrays.asList(new Video("name"));
  }
}
