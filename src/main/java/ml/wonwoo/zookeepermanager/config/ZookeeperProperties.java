package ml.wonwoo.zookeepermanager.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

@ConfigurationProperties("zookeeper")
public class ZookeeperProperties {

  private String connectString = "127.0.0.1:2181";
  private Integer baseSleepTimeMs = 2000;
  private Integer maxRetries = 5;
  private Integer maxSleepMs = 5000;
  private Integer blockUntilConnectedWait = 10;
  private TimeUnit blockUntilConnectedUnit = TimeUnit.SECONDS;
  private Admin admin = new Admin();

  public String getConnectString() {
    return connectString;
  }

  public void setConnectString(String connectString) {
    this.connectString = connectString;
  }

  public Integer getBaseSleepTimeMs() {
    return baseSleepTimeMs;
  }

  public void setBaseSleepTimeMs(Integer baseSleepTimeMs) {
    this.baseSleepTimeMs = baseSleepTimeMs;
  }

  public Integer getMaxRetries() {
    return maxRetries;
  }

  public void setMaxRetries(Integer maxRetries) {
    this.maxRetries = maxRetries;
  }

  public Integer getMaxSleepMs() {
    return maxSleepMs;
  }

  public void setMaxSleepMs(Integer maxSleepMs) {
    this.maxSleepMs = maxSleepMs;
  }

  public Integer getBlockUntilConnectedWait() {
    return blockUntilConnectedWait;
  }

  public void setBlockUntilConnectedWait(Integer blockUntilConnectedWait) {
    this.blockUntilConnectedWait = blockUntilConnectedWait;
  }

  public TimeUnit getBlockUntilConnectedUnit() {
    return blockUntilConnectedUnit;
  }

  public void setBlockUntilConnectedUnit(TimeUnit blockUntilConnectedUnit) {
    this.blockUntilConnectedUnit = blockUntilConnectedUnit;
  }

  public void setAdmin(Admin admin) {
    this.admin = admin;
  }

  public Admin getAdmin() {
    return admin;
  }

  public static class Admin {
    private String uri = "http://localhost:8080/commands";

    public String getUri() {
      return uri;
    }

    public void setUri(String uri) {
      this.uri = uri;
    }
  }
}
