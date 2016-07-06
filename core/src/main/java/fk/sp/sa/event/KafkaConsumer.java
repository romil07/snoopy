package fk.sp.sa.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by romil.goyal on 06/07/16.
 */
@Data
public class KafkaConsumer {

    org.apache.kafka.clients.consumer.KafkaConsumer<String, String> kafkaConsumer;
    KafkaConsumerConfigReader kafkaConsumerConfigReader;
    ObjectMapper mapper;
    List<ConsumerRecord> consumedRecords;

    @Inject
    public KafkaConsumer(KafkaConsumerConfigReader kafkaConsumerConfigReader) {
        this.kafkaConsumerConfigReader = kafkaConsumerConfigReader;
    }

    public void init() {
        KafkaConsumerConfig config = kafkaConsumerConfigReader.readConfig();
        kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(config.getConfig());
        kafkaConsumer.subscribe(Arrays.asList("snoopy1"));
    }

    public void resetOffsetToStart() {
        kafkaConsumer.seekToBeginning(new TopicPartition("snoopy1", 0));
    }

    public Message consume() {
        try {
            while (true) {
                if (consumedRecords.size() > 0) {
                    return mapper.readValue(consumedRecords.get(0).value().toString(), Message.class);
                } else {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                    consumedRecords = Lists.newArrayList(records);
                    return mapper.readValue(consumedRecords.get(0).value().toString(), Message.class);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
