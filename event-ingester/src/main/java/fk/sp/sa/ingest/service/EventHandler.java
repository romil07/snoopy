package fk.sp.sa.ingest.service;

import com.google.inject.Inject;
import fk.sp.sa.event.EventTransformer;
import fk.sp.sa.event.RoutingConfiguration;
import fk.sp.sa.event.input.InputEvent;
import fk.sp.sa.event.output.OutputEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EventHandler {
    private Map<String,Set<EventTransformer>> eventTransformers;
    private RoutingConfiguration routingConfiguration;
    @Inject
    public EventHandler(Map<String,Set<EventTransformer>> eventTransformers,RoutingConfiguration routingConfiguration){
        this.eventTransformers = eventTransformers;
        this.routingConfiguration = routingConfiguration;
    }

    public void handleEvent(InputEvent inputEvent){
        Set<OutputEvent> outputEvents = transformEvents(inputEvent);


    }

    private Set<OutputEvent> transformEvents(InputEvent inputEvent){
        String eventType = inputEvent.getEventType();
        Set<EventTransformer> eventTransformerSet = eventTransformers.get(eventType);
        if(eventTransformerSet!=null){
            //apply all transformers to input event
            Set<OutputEvent> outputEvents = eventTransformerSet.parallelStream().map(eventTransformer -> eventTransformer.transform(inputEvent)).collect(Collectors.toSet());
            return outputEvents;
        }
        return null;
    }



}