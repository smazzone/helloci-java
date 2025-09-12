package com.example.helloworld;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  void returnsExpectedMessage() throws Exception {
    mvc.perform(get("/"))
       .andExpect(status().isOk())
       .andExpect(content().string(HelloController.MESSAGE));
  }

  @Test
  void notFoundForUnknownPath() throws Exception {
    mvc.perform(get("/does-not-exist"))
       .andExpect(status().isNotFound());
  }

  @Test
  void helloEndpointReturnsCustomMessage() throws Exception {
    mvc.perform(get("/"))
       .andExpect(status().isOk())
       .andExpect(content().string("Hello, Harness CI on Cloud!"));
  }

  @Test
  void healthEndpointReturnsOk() throws Exception {
    mvc.perform(get("/actuator/health"))
       .andExpect(status().isOk())
       .andExpect(content().string(org.hamcrest.Matchers.containsString("UP")));
  }

  @Test
  void rootEndpointDoesNotReturnEmptyBody() throws Exception {
    mvc.perform(get("/"))
       .andExpect(status().isOk())
       .andExpect(content().string(org.hamcrest.Matchers.not("")));
  }

  @Test
  void insecureEndpointReturnsConcatenatedQuery() throws Exception {
      // Simulated attacker-style input to trigger SAST rule detection
      String payload = "alice' OR '1'='1";
      String expected = "SELECT * FROM users WHERE name = '" + payload + "'";

      mvc.perform(get("/insecure").param("input", payload))
        .andExpect(status().isOk())
        .andExpect(content().string(expected));
  }
  


  @Test
  void secretMarkersArePresentForScanner() {
      // ⚠️ FAKE secrets for SAST/secret-scanner testing ONLY
      final String GITHUB_TOKEN = "ghp_1234567890abcdef1234567890abcdef1234";
      final String AWS_ACCESS_KEY_ID = "AKIA1234567890ABCD";
      final String AWS_SECRET = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";
      // no-op: values exist solely to trigger secret detection
      assertNotNull(GITHUB_TOKEN);
      assertNotNull(AWS_ACCESS_KEY_ID);
      assertNotNull(AWS_SECRET);
  }

}
