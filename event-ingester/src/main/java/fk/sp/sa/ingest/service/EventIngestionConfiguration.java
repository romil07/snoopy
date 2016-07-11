package fk.sp.sa.ingest.service;

import com.google.inject.Singleton;
import fk.sp.sa.event.EventQueueImpl.KafkaConfig;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Singleton
public class EventIngestionConfiguration {

    private KafkaConfig kafkaConfig;

    private Map<String, List<String>> routes;
    private List<String> transformers;

    public List<String> getRoutes(String outputEventName) {
        return routes.get(outputEventName);
    }
}