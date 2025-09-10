package com.example.helloworld;

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
       .andExpect(content().string("Hello, Harness CI on Cloud!")); // adjust if your controller returns something else
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

}
