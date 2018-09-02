package com.github.dhiraj072.yrandom.video;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class VideoControllerRestAPITest {

  @Test
  public void testReturnsAGoodRandomVideo() {

    when().get("/video/random").then()
        .statusCode(200)
        .assertThat().body(matchesJsonSchemaInClasspath("RestAPI/video.json"));
  }
}
