package fk.sp.sa.event.impl;

import com.google.inject.Inject;
import fk.sp.sa.event.EventProducer;
import fk.sp.sa.event.Message;
import lombok.Data;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * Created by romil.goyal on 05/07/16.
 */
@Data
class KafkaEventProducer implements EventProducer {

    private Producer<String, String> kafkaProducer;
    private KafkaProducerConfig kafkaConfig;

    @Inject
    public KafkaEventProducer(KafkaProducerConfig kafkaConfig) {
        this.kafkaConfig = kafkaConfig;
    }

    public void init() {
        kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer<>(kafkaConfig.getKafkaConfig());
    }

    @Override
    public void put(Message message) {
        kafkaProducer.send(new ProducerRecord<String, String>("snoopy1", message.toString()));
    }
}
