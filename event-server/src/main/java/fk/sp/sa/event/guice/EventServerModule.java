package fk.sp.sa.event.guice;

import com.google.inject.AbstractModule;

public class EventServerModule extends AbstractModule {

    @Override
    protected void configure() {
        //bind(EventQueue.class).to(KafkaEventQueue.class);
    }
}
