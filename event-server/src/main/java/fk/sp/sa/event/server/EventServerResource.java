package fk.sp.sa.event.server;

import com.google.inject.Inject;
import fk.sp.sa.event.EventProducer;
import fk.sp.sa.event.Message;
import fk.sp.sa.event.input.InputEvent;
import fk.sp.sa.event.EventConsumer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/event")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EventServerResource {

    private EventProducer eventProducer;
    private EventConsumer eventConsumer;

    @Inject
    public EventServerResource(EventProducer eventProducer, EventConsumer eventConsumer) {
        this.eventProducer = eventProducer;
        this.eventConsumer = eventConsumer;
    }

    @POST
    @Path("/ingest")
    public void ingestEvent(InputEvent inputEvent) {
        System.out.println(inputEvent);
        eventProducer.put(new Message(inputEvent));
    }

    @GET
    @Path("/get")
    public void getEvent() {
        Object o = eventConsumer.getNext();
        System.out.println(o);
    }
}
