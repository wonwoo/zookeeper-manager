package ml.wonwoo.zookeepermanager.web;

import ml.wonwoo.zookeepermanager.config.ZookeeperProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = AdminController.class, secure = false)
@RunWith(SpringRunner.class)
@AutoConfigureMockRestServiceServer
@AutoConfigureWebClient
public class AdminControllerTests {

  @Autowired
  private MockRestServiceServer server;

  @Autowired
  private MockMvc mvc;

  @Test
  public void connectionsTest() throws Exception {
    this.server.expect(
        requestTo("/stats"))
        .andRespond(withSuccess(
            new ClassPathResource("stats.json", getClass()),
            MediaType.APPLICATION_JSON));
    this.mvc.perform(get("/admin"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("127.0.0.1")))
        .andExpect(content().string(containsString("localhost")))
        .andExpect(content().string(containsString("1")))
        .andExpect(content().string(containsString("0")))
        .andExpect(content().string(containsString("3")));
  }

  @TestConfiguration
  static class ConfigTest {

    @Bean
    ZookeeperProperties zookeeperProperties() {
      return new ZookeeperProperties();
    }
  }

}