package ml.wonwoo.zookeepermanager.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stats {

  private final List<Connection> connections;
  private final ServerStats serverStats;

  @JsonCreator
  public Stats(@JsonProperty("connections") List<Connection> connections,
               @JsonProperty("server_stats") ServerStats serverStats) {
    this.connections = connections;
    this.serverStats = serverStats;
  }

  public List<Connection> getConnections() {
    return connections;
  }

  public ServerStats getServerStats() {
    return serverStats;
  }
}
