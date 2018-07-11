package ml.wonwoo.zookeepermanager.admin;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatsTests {

  private String json = "{\n" +
      "    \"version\": \"3.5.4-beta-7f51e5b68cf2f80176ff944a9ebd2abbc65e7327, built on 05/11/2018 16:27 GMT\",\n" +
      "    \"read_only\": false,\n" +
      "    \"server_stats\": {\n" +
      "        \"packets_sent\": 20,\n" +
      "        \"packets_received\": 20,\n" +
      "        \"max_latency\": 49,\n" +
      "        \"min_latency\": 0,\n" +
      "        \"data_dir_size\": 134218847,\n" +
      "        \"log_dir_size\": 134218847,\n" +
      "        \"last_processed_zxid\": 52,\n" +
      "        \"outstanding_requests\": 0,\n" +
      "        \"avg_latency\": 3,\n" +
      "        \"server_state\": \"standalone\",\n" +
      "        \"num_alive_client_connections\": 1,\n" +
      "        \"provider_null\": false\n" +
      "    },\n" +
      "    \"node_count\": 7,\n" +
      "    \"connections\": [\n" +
      "        {\n" +
      "            \"remote_socket_address\": {\n" +
      "                \"address\": \"localhost\",\n" +
      "                \"port\": 63354,\n" +
      "                \"unresolved\": false,\n" +
      "                \"host_name\": \"localhost\",\n" +
      "                \"host_string\": \"localhost\"\n" +
      "            },\n" +
      "            \"interest_ops\": 1,\n" +
      "            \"outstanding_requests\": 0,\n" +
      "            \"packets_received\": 20,\n" +
      "            \"packets_sent\": 20\n" +
      "        }\n" +
      "    ],\n" +
      "    \"command\": \"stats\",\n" +
      "    \"error\": null\n" +
      "}";

  @Test
  public void converterTest() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    Stats stats = objectMapper.readerFor(Stats.class).readValue(json);
    assertThat(stats).isNotNull();
    assertThat(stats.getServerStats().getAliveConnections()).isEqualTo(1);
    assertThat(stats.getConnections()).hasSize(1);
    assertThat(stats.getConnections().get(0).getRemoteAddress().getAddress()).isEqualTo("localhost");
    assertThat(stats.getConnections().get(0).getRemoteAddress().getPort()).isEqualTo(63354);
    assertThat(stats.getConnections().get(0).getRemoteAddress().getHostName()).isEqualTo("localhost");
    assertThat(stats.getConnections().get(0).getRemoteAddress().getHostString()).isEqualTo("localhost");
    assertThat(stats.getConnections().get(0).getInterestOps()).isEqualTo(1);
    assertThat(stats.getConnections().get(0).getOutstandingRequests()).isEqualTo(0);
    assertThat(stats.getConnections().get(0).getPacketsSent()).isEqualTo(20);
    assertThat(stats.getConnections().get(0).getPacketsReceived()).isEqualTo(20);
  }

}