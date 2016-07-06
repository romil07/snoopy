package fk.sp.sa.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * Created by romil.goyal on 05/07/16.
 */
@Data
@AllArgsConstructor
public class KafkaConsumerConfig {

    private Map<String, Object> config;

//  @JsonProperty("bootstrap.servers")
//  private String bootstrapServers;
//
//  @JsonProperty("group.id")
//  private String groupId;
//
//  @JsonProperty("enable.auto.commit")
//  private String enableAutoCommit;
//
//  @JsonProperty("auto.commit.interval.ms")
//  private String autoCommitIntervalMs;
//
//  @JsonProperty("session.timeout.ms")
//  private String sessionTimeoutMs;
//
//  @JsonProperty("key.deserializer")
//  private String keyDeserializer;
//
//  @JsonProperty("value.deserializer")
//  private String valueDeserializer;

}
