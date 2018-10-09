package com.github.dhiraj072.yrandom.video;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="youtube-service", url = "localhost:8081")
public interface YoutubeServiceProxy {

  @GetMapping("/youtube/video/random")
  Map<String, Object> getRandomVideo();
}
