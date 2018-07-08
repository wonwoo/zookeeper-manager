package ml.wonwoo.zookeepermanager.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerStats {

  private final Integer aliveConnections;

  @JsonCreator
  public ServerStats(@JsonProperty("num_alive_client_connections") Integer aliveConnections) {
    this.aliveConnections = aliveConnections;
  }

  public Integer getAliveConnections() {
    return aliveConnections;
  }
}
