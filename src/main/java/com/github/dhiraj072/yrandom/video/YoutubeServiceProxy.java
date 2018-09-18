package com.github.dhiraj072.yrandom.video;

import com.google.api.services.youtube.model.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="youtube-service", url = "localhost:8081")
public interface YoutubeServiceProxy {

  @GetMapping("/youtube/video/random")
  Video getRandomVideo();
}
