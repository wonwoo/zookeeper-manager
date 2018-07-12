package ml.wonwoo.zookeepermanager.web;

import ml.wonwoo.zookeepermanager.zookeeper.Zoo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = IndexController.class, secure = false)
@RunWith(SpringRunner.class)
public class IndexControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private Zoo zoo;

  @Test
  public void indexTest() throws Exception {
    given(zoo.zooChild(anyString()))
        .willReturn(Arrays.asList("wonwoo", "zoo1", "zoo2"));

    this.mvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("wonwoo")))
        .andExpect(content().string(containsString("zoo1")))
        .andExpect(content().string(containsString("zoo2")));
  }
}