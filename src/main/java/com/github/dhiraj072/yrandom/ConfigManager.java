package com.github.dhiraj072.yrandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigManager {

  @Value("${client.secret}")
  private String clientSecret;

  public String getClientSecret() {

    return clientSecret;
  }
}
