package com.github.dhiraj072.yrandom.video;

import java.util.Map;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="youtube-service")
@RibbonClient(name="youtube-service")
public interface YoutubeServiceProxy {

  @GetMapping("/youtube/video/random")
  Map<String, Object> getRandomVideo();
}
