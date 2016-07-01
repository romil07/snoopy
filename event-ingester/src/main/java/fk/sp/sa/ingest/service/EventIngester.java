package fk.sp.sa.ingest.service;

import fk.sp.sa.event.EventQueue;
import fk.sp.sa.event.Message;

public class EventIngester {
    private EventQueue eventQueue;
    private EventHandler eventHandler;
    private volatile boolean stop;
    public EventIngester(EventQueue eventQueue,EventHandler eventHandler){
        this.eventHandler = eventHandler;
        this.eventQueue = eventQueue;
    }
    public void init(){

    }

    public void start(){
        while (!stop){
            Message message = eventQueue.getNext();
            eventHandler.handleEvent(message.getInputEvent());
        }
    }
    public void stop(){
        stop = true;
    }
}
