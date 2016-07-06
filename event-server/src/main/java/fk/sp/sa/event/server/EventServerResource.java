package fk.sp.sa.event.server;

import com.google.inject.Inject;
import fk.sp.sa.event.EventQueue;
import fk.sp.sa.event.Message;
import fk.sp.sa.event.input.InputEvent;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/event")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventServerResource {

    private EventQueue eventQueue;

    @Inject
    public EventServerResource(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @POST
    @Path("/ingest")
    public void ingestEvent(InputEvent inputEvent) {
        System.out.println(inputEvent);
        eventQueue.put(new Message(inputEvent));
    }

    @GET
    @Path("/get")
    public void getEvent() {
        Object o = eventQueue.getNext();
        System.out.println(o);
    }
}
