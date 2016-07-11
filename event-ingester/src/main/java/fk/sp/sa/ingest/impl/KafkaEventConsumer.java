package fk.sp.sa.ingest.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import fk.sp.sa.event.Message;
import fk.sp.sa.event.EventConsumer;
import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by romil.goyal on 06/07/16.
 */
@Data
class KafkaEventConsumer implements EventConsumer {

    private org.apache.kafka.clients.consumer.KafkaConsumer<String, String> kafkaConsumer;
    private ObjectMapper mapper;
    private LinkedBlockingQueue<ConsumerRecord<String, String>> blockingQueue;
    private List<ConsumerRecord> consumedRecords;
    private KafkaConsumerConfig kafkaConfig;

    @Inject
    public KafkaEventConsumer(KafkaConsumerConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    @Override
    public Message getNext() {
        try {
            while (true) {
                if (consumedRecords.size() > 0) {
                    return mapper.readValue(consumedRecords.get(0).value().toString(), Message.class);
                } else {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                    blockingQueue = new LinkedBlockingQueue<>();
                    for (ConsumerRecord record : records) {
                        blockingQueue.put(record);
                    }
                    ConsumerRecord<String, String> consumedRecord = blockingQueue.poll();
                    return mapper.readValue(consumedRecord.value().toString(), Message.class);
                }
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void init() {
        kafkaConsumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(kafkaConfig.getKafkaConfig());
        kafkaConsumer.subscribe(Arrays.asList("snoopy1"));
    }

    @Override
    public void reset() {
        kafkaConsumer.seekToBeginning(new TopicPartition("snoopy1", 0));
    }

    @Override
    public void reset(int offset) {
        kafkaConsumer.seekToBeginning(new TopicPartition("snoopy1", offset));
    }
}
