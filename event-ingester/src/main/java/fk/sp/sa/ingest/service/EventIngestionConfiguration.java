package fk.sp.sa.ingest.service;

import com.google.inject.Singleton;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Singleton
public class EventIngestionConfiguration {

    private Map<String, List<String>> routes;

    public List<String> getRoutes(String outputEventName) {
        return routes.get(outputEventName);
    }
}