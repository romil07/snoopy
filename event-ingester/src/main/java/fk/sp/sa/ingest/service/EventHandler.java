package fk.sp.sa.ingest.service;

import com.google.inject.Inject;
import fk.sp.sa.event.EventTransformer;
import fk.sp.sa.event.input.InputEvent;
import fk.sp.sa.event.output.OutputEvent;
import fk.sp.sa.ingest.storage.StorageAdaptor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class EventHandler {

    private Map<String, Set<EventTransformer>> eventTransformers;
    private EventIngestionConfiguration eventIngestionConfiguration;
    private StorageAdaptor storageAdaptor;

    @Inject
    public EventHandler(Map<String, Set<EventTransformer>> eventTransformers,
                        EventIngestionConfiguration eventIngestionConfiguration,
                        StorageAdaptor storageAdaptor) {
        this.eventTransformers = eventTransformers;
        this.eventIngestionConfiguration = eventIngestionConfiguration;
        this.storageAdaptor = storageAdaptor;
    }

    public void handleEvent(InputEvent inputEvent) {
        Set<OutputEvent> outputEvents = transformEvents(inputEvent);
        log.debug("Pushing events " + outputEvents);
        outputEvents.parallelStream().forEach(outputEvent -> storageAdaptor.push(outputEvent));
        log.debug("Pushed events " + outputEvents);
    }

    private Set<OutputEvent> transformEvents(InputEvent inputEvent) {
        String eventType = inputEvent.getEventType();
        Set<EventTransformer> eventTransformerSet = eventTransformers.get(eventType);
        if (eventTransformerSet != null) {
            //apply all transformers to input event
            Set<OutputEvent> outputEvents = eventTransformerSet.parallelStream().map(
                    eventTransformer -> eventTransformer.transform(inputEvent)).collect(
                    Collectors.toSet());
            return outputEvents;
        }
        return null;
    }
}