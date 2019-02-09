package com.github.dhiraj072.yrandom.video;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.RestAssured;
import java.util.HashMap;
import javax.annotation.Resource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class VideoControllerRestAPITest {

  @LocalServerPort
  private int serverPort;

  @Mock
  private YoutubeServiceProxy youtubeServiceProxy;

  @InjectMocks
  @Resource
  private VideoController videoController;

  @BeforeAll
  void setUp() {

    RestAssured.port = serverPort;
    MockitoAnnotations.initMocks(this);
    Mockito.when(youtubeServiceProxy.getRandomVideo()).thenReturn(
        new HashMap<String, Object>() {{ put ("test", "value"); }} );
  }

  @Test
  void testReturnsAGoodVideo() {

    when().get("/yrandom/video/random").then()
        .statusCode(200)
        .assertThat().body("test", equalTo("value"));
  }
}
