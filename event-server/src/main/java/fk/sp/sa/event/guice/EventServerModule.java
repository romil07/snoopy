package fk.sp.sa.event.guice;

import com.google.inject.AbstractModule;

import fk.sp.sa.event.EventQueue;

public class EventServerModule extends AbstractModule {

  @Override
  protected void configure() {
      //bind(EventQueue.class).to(KafkaEventQueue.class);
  }
}
