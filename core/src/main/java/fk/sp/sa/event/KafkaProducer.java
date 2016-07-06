package fk.sp.sa.event;

import com.google.inject.Inject;
import lombok.Data;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;

/**
 * Created by romil.goyal on 05/07/16.
 */
@Data
public class KafkaProducer {

    Producer<String, String> kafkaProducer;
    KafkaProducerConfigReader kafkaProducerConfigReader;

    @Inject
    public KafkaProducer(KafkaProducerConfigReader kafkaProducerConfigReader,
                         KafkaConsumerConfigReader kafkaConsumerConfigReader) {
        this.kafkaProducerConfigReader = kafkaProducerConfigReader;
    }

    public void init() {
        KafkaProducerConfig config = kafkaProducerConfigReader.readConfig();
        kafkaProducer = new org.apache.kafka.clients.producer.KafkaProducer<>(config.getConfig());
    }

    public void pushEvent(String message) {
        kafkaProducer.send(new ProducerRecord<String, String>("snoopy1", message));
    }
}
