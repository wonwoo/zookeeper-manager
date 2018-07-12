package ml.wonwoo.zookeepermanager.web;


import ml.wonwoo.zookeepermanager.zookeeper.ExistNodeException;
import ml.wonwoo.zookeepermanager.zookeeper.Zoo;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ManagerController.class, secure = false)
@RunWith(SpringRunner.class)
public class ManagerControllerTests {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private Zoo zoo;

  @Test
  public void createZooTest() throws Exception {
    given(zoo.createNode(anyString(), any()))
        .willReturn("test");
    this.mvc.perform(post("/")
        .param("path", "test,1")
        .param("createMode", CreateMode.EPHEMERAL.name()))
        .andExpect(status().isFound());
    verify(zoo).createNode("test/1", CreateMode.EPHEMERAL);
  }

  @Test
  public void createZooExistNodeExceptionTest() throws Exception {
    given(zoo.createNode(anyString(), any()))
        .willThrow(ExistNodeException.class);
    this.mvc.perform(post("/")
        .param("path", "test,1")
        .param("createMode", CreateMode.EPHEMERAL.name()))
        .andExpect(status().isFound());
    verify(zoo).createNode("test/1", CreateMode.EPHEMERAL);
  }


  @Test
  public void getZooTest() throws Exception {
    given(zoo.getData(any()))
        .willReturn(new byte[]{11, 22, 33});
    given(zoo.zooChild(any()))
        .willReturn(Arrays.asList("wonwoo", "test1"));
    given(zoo.existNode(any()))
        .willReturn(new Stat(10L, 10L, 10L, 10L, 10, 10, 10, 10L, 10, 10, 10L));
    this.mvc.perform(get("/zoo")
        .param("path", "test"))
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("wonwoo")))
        .andExpect(content().string(containsString("test1")))
        .andExpect(content().string(containsString("10")))
        .andExpect(content().string(containsString("test")));
  }

  @Test
  public void createDataTest() throws Exception {
    given(zoo.setData(any(), any()))
        .willReturn(new Stat(10L, 10L, 10L, 10L, 10, 10, 10, 10L, 10, 10, 10L));
    this.mvc.perform(post("/zoo")
        .param("path", "test")
        .param("data", "127.0.0.1"))
        .andExpect(status().isFound());
    verify(zoo).setData("test", "127.0.0.1".getBytes());
  }

  @Test
  public void deleteZooTest() throws Exception {
    willDoNothing().given(zoo).deleteNode("test", true);
    this.mvc.perform(delete("/zoo")
        .param("path", "test")
        .param("isChild", "true"))
        .andExpect(status().isFound());
    verify(zoo).deleteNode("test", true);
  }
}