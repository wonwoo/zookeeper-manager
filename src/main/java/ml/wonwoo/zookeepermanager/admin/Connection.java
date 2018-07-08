package ml.wonwoo.zookeepermanager.admin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Connection {

  private final Integer interestOps;
  private final Integer outstandingRequests;
  private final Integer packetsReceived;
  private final Integer packetsSent;
  private final RemoteAddress remoteAddress;


  @JsonCreator
  public Connection(@JsonProperty("interest_ops") Integer interestOps,
                    @JsonProperty("outstanding_requests") Integer outstandingRequests,
                    @JsonProperty("packets_received") Integer packetsReceived,
                    @JsonProperty("packets_sent") Integer packetsSent,
                    @JsonProperty("remote_socket_address") RemoteAddress remoteAddress) {
    this.interestOps = interestOps;
    this.outstandingRequests = outstandingRequests;
    this.packetsReceived = packetsReceived;
    this.packetsSent = packetsSent;
    this.remoteAddress = remoteAddress;
  }

  public Integer getInterestOps() {
    return interestOps;
  }

  public Integer getOutstandingRequests() {
    return outstandingRequests;
  }

  public Integer getPacketsReceived() {
    return packetsReceived;
  }

  public Integer getPacketsSent() {
    return packetsSent;
  }

  public RemoteAddress getRemoteAddress() {
    return remoteAddress;
  }
}
