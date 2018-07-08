package ml.wonwoo.zookeepermanager.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteAddress {

  private final String address;
  private final Integer port;
  private final boolean unresolved;
  private final String hostName;
  private final String hostString;

  @JsonCreator
  public RemoteAddress(@JsonProperty("address") String address, @JsonProperty("port") Integer port,
                       @JsonProperty("unresolved") boolean unresolved,
                       @JsonProperty("host_name") String hostName,
                       @JsonProperty("host_string") String hostString) {
    this.address = address;
    this.port = port;
    this.unresolved = unresolved;
    this.hostName = hostName;
    this.hostString = hostString;
  }

  public String getAddress() {
    return address;
  }

  public Integer getPort() {
    return port;
  }

  public boolean isUnresolved() {
    return unresolved;
  }

  public String getHostName() {
    return hostName;
  }

  public String getHostString() {
    return hostString;
  }
}
