package fk.sp.sa.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
* Created by romil.goyal on 05/07/16.
*/
@Data
@AllArgsConstructor
public class KafkaProducerConfig {

    private Map<String, Object> config;

//  @JsonProperty("bootstrap.servers")
//  private String bootstrapServers;
//
//  @JsonProperty("acks")
//  private String acks;
//
//  @JsonProperty("retries")
//  private String retries;
//
//  @JsonProperty("batch.size")
//  private String batchSize;
//
//  @JsonProperty("linger.ms")
//  private String lingerMs;
//
//  @JsonProperty("buffer.memory")
//  private String bufferMemory;
//
//  @JsonProperty("key.serializer")
//  private String keySerializer;
//
//  @JsonProperty("value.serializer")
//  private String valueSerializer;

}
