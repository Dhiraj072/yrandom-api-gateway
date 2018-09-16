package com.github.dhiraj072.yrandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigManager {

  @Value("${spring.application.name}")
  private String appName;

  public String getAppName() {

    return appName;
  }
}
