package ml.wonwoo.zookeepermanager.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ZookeeperProperties.class)
public class ZookeeperConfiguration {

  private final ZookeeperProperties zookeeperProperties;

  public ZookeeperConfiguration(ZookeeperProperties zookeeperProperties) {
    this.zookeeperProperties = zookeeperProperties;
  }

  @Bean
  public CuratorFramework curatorFramework(RetryPolicy retryPolicy) throws Exception {
    CuratorFramework curator = CuratorFrameworkFactory
        .builder()
        .retryPolicy(retryPolicy)
        .connectString(zookeeperProperties.getConnectString())
        .build();
    curator.start();
    curator.blockUntilConnected(zookeeperProperties.getBlockUntilConnectedWait(),
        zookeeperProperties.getBlockUntilConnectedUnit());
    return curator;
  }

  @Bean
  public RetryPolicy exponentialBackoffRetry() {
    return new ExponentialBackoffRetry(
        zookeeperProperties.getBaseSleepTimeMs(),
        zookeeperProperties.getMaxRetries(),
        zookeeperProperties.getMaxSleepMs());
  }

}
