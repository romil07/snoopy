package fk.sp.sa.event.server;

import com.google.inject.Inject;
import fk.sp.sa.event.EventQueue;
import fk.sp.sa.event.Message;
import fk.sp.sa.event.input.InputEvent;
import javax.ws.rs.POST;
import javax.ws.rs.Path;



@Path("/event")
public class EventServerResource  {
    private EventQueue eventQueue;
    @Inject
    public EventServerResource(EventQueue eventQueue){
        this.eventQueue = eventQueue;
    }

    @POST
    public void ingestEvent(InputEvent inputEvent){
            eventQueue.put(new Message(inputEvent));
    }
}
