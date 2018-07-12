package ml.wonwoo.zookeepermanager;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.apache.curator.test.TestingServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperManagerApplicationTests {

  @Autowired
  private TestingServer zkTestServer;

  private CuratorFramework curatorFramework;

  @Before
  public void setup()  {
    curatorFramework = CuratorFrameworkFactory.newClient(zkTestServer.getConnectString(), new RetryOneTime(2000));
  }

  @Test
  public void contextLoads() {
    assertThat(zkTestServer).isNotNull();
  }

  @After
  public void stop() throws IOException {
    zkTestServer.stop();
    curatorFramework.close();
  }

  @Configuration
  public static class ZooConfig {

    @Bean
    TestingServer zkTestServer() throws Exception {
      return new TestingServer(2181);
    }
  }

}
