package com.github.dhiraj072.yrandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.github.dhiraj072.yrandom.video")
public class YrandomApplication {

	public static void main(String[] args) {
		SpringApplication.run(YrandomApplication.class, args);
	}
}
