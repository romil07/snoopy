package fk.sp.sa.ingest.impl;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * Created by romil.goyal on 11/07/16.
 */
@Data
@AllArgsConstructor
public class KafkaConsumerConfig {

    private Map<String, Object> kafkaConfig;
}
