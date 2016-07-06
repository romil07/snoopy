package fk.sp.sa.event;

import com.google.inject.Inject;

import java.io.IOException;

/**
 * Created by romil.goyal on 05/07/16.
 */
public class KafkaQueue implements EventQueue {

    fk.sp.sa.event.KafkaProducer producer;
    fk.sp.sa.event.KafkaConsumer consumer;

    @Inject
    public KafkaQueue(fk.sp.sa.event.KafkaProducer producer, fk.sp.sa.event.KafkaConsumer consumer)
            throws IOException {
        this.consumer = consumer;
        this.producer = producer;
        producer.init();
        consumer.init();
    }

    @Override
    public Message getNext() {
        return consumer.consume();
    }

    @Override
    public void init() {
        initProducer();
        initConsumer();
    }

    private void initProducer() {
        producer.init();
    }

    private void initConsumer() {
        consumer.init();
    }

    @Override
    public void reset() {

    }

    @Override
    public void reset(int offset) {
        consumer.resetOffsetToStart();
    }

    @Override
    public void put(Message message) {
        producer.pushEvent(message.toString());
    }
}
