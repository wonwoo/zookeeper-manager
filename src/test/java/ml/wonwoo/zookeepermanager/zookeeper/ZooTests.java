package ml.wonwoo.zookeepermanager.zookeeper;


import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ZooTests {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private CuratorFramework curator;

  private Zoo zoo;

  @Before
  public void setup() {
    this.zoo = new Zoo(this.curator);
  }

  @Test
  public void zooChildTest() throws Exception {
    given(this.curator.getChildren().forPath(anyString()))
        .willReturn(Arrays.asList("test", "test1"));
    List<String> zooChild = this.zoo.zooChild("test");
    assertThat(zooChild.get(0)).isEqualTo("test");
    assertThat(zooChild.get(1)).isEqualTo("test1");
  }

  @Test
  public void zooChildExceptionTest() throws Exception {
    exception.expect(ZookeeperException.class);
    given(this.curator.getChildren().forPath(anyString()))
        .willThrow(Exception.class);
    this.zoo.zooChild("test");
  }


  @Test
  public void createNodeTest() throws Exception {
    given(this.curator.create().withMode(any()).forPath(any(), any()))
        .willReturn("wonwoo");
    given(this.curator.checkExists().forPath(any())).willReturn(null);
    String node = this.zoo.createNode("wonwoo", CreateMode.EPHEMERAL);
    assertThat(node).isEqualTo("wonwoo");
  }

  @Test
  public void createNodeExceptionTest() throws Exception {
    exception.expect(ZookeeperException.class);
    given(this.curator.checkExists().forPath(any())).willReturn(null);
    given(this.curator.create().withMode(any()).forPath(any(), any()))
        .willThrow(Exception.class);
    this.zoo.createNode("wonwoo", CreateMode.EPHEMERAL);
  }

  @Test
  public void setDataTest() throws Exception {
    given(this.curator.setData().forPath(any(), any()))
        .willReturn(new Stat(10L, 10L, 10L, 10L, 10, 10, 10, 10L, 10, 10, 10L));
    Stat stat = this.zoo.setData("wonwoo", new byte[]{});
    assertThat(stat.getAversion()).isEqualTo(10);
    assertThat(stat.getCtime()).isEqualTo(10);
    assertThat(stat.getCversion()).isEqualTo(10);
    assertThat(stat.getCzxid()).isEqualTo(10);
    assertThat(stat.getDataLength()).isEqualTo(10);
    assertThat(stat.getEphemeralOwner()).isEqualTo(10);
    assertThat(stat.getMtime()).isEqualTo(10);
    assertThat(stat.getMzxid()).isEqualTo(10);
    assertThat(stat.getNumChildren()).isEqualTo(10);
    assertThat(stat.getPzxid()).isEqualTo(10);
    assertThat(stat.getVersion()).isEqualTo(10);
  }

  @Test
  public void setDataTestException() throws Exception {
    exception.expect(ZookeeperException.class);
    given(this.curator.setData().forPath(any(), any()))
        .willThrow(Exception.class);
    this.zoo.setData("wonwoo", new byte[]{});
  }

  @Test
  public void getDataTest() throws Exception {
    given(this.curator.getData().forPath(any()))
        .willReturn(new byte[]{21, 22, 23});
    byte[] data = this.zoo.getData("wonwoo");
    assertThat(data).isEqualTo(new byte[]{21, 22, 23});
  }

  @Test
  public void getDataExceptionTest() throws Exception {
    exception.expect(NotFoundException.class);
    given(this.curator.getData().forPath(any()))
        .willThrow(Exception.class);
    this.zoo.getData("wonwoo");
  }

  @Test
  public void existNodeTest() throws Exception {
    given(curator.checkExists().forPath(any()))
        .willReturn(new Stat(10L, 10L, 10L, 10L, 10, 10, 10, 10L, 10, 10, 10L));
    Stat stat = this.zoo.existNode("wonwoo");
    assertThat(stat.getAversion()).isEqualTo(10);
    assertThat(stat.getCtime()).isEqualTo(10);
    assertThat(stat.getCversion()).isEqualTo(10);
    assertThat(stat.getCzxid()).isEqualTo(10);
    assertThat(stat.getDataLength()).isEqualTo(10);
    assertThat(stat.getEphemeralOwner()).isEqualTo(10);
    assertThat(stat.getMtime()).isEqualTo(10);
    assertThat(stat.getMzxid()).isEqualTo(10);
    assertThat(stat.getNumChildren()).isEqualTo(10);
    assertThat(stat.getPzxid()).isEqualTo(10);
    assertThat(stat.getVersion()).isEqualTo(10);

  }

  @Test
  public void existNodeExceptionTest() throws Exception {
    exception.expect(ZookeeperException.class);
    given(curator.checkExists().forPath(any()))
        .willThrow(Exception.class);
    this.zoo.existNode("wonwoo");
  }
}