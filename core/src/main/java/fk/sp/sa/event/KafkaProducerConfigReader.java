package fk.sp.sa.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;

/**
 * Created by romil.goyal on 05/07/16.
 */
public class KafkaProducerConfigReader {

    ObjectMapper mapper;

    public KafkaProducerConfigReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public KafkaProducerConfig readConfig() {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        KafkaProducerConfig
                config = null;
        try {
            config = mapper.readValue(new File("/config/kafka-producer.yml"), KafkaProducerConfig.class);
        } catch (Exception e) {
        }
        return config;
    }

}
