package fk.sp.sa.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

/**
 * Created by romil.goyal on 06/07/16.
 */
public class KafkaConsumerConfigReader {

    ObjectMapper mapper;

    public KafkaConsumerConfigReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public KafkaConsumerConfig readConfig() {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        KafkaConsumerConfig config = null;
        try {
            config = mapper.readValue(new File("/config/kafka-consumer.yml"), KafkaConsumerConfig.class);
        } catch (Exception e) {
        }

        return config;
    }
}
