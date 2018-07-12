package ml.wonwoo.zookeepermanager;

import org.apache.curator.test.TestingServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZookeeperManagerApplicationTests {

  private static TestingServer zkTestServer;

  @BeforeClass
  public static void setup() throws Exception {
    zkTestServer = new TestingServer(2181);
  }

  @Test
  public void contextLoads() {
    assertThat(zkTestServer).isNotNull();
  }

  @AfterClass
  public static void stop() throws IOException {
    zkTestServer.stop();
  }
}
