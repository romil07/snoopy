package fk.sp.sa.ingest.guice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import fk.sp.sa.event.EventQueue;
import fk.sp.sa.ingest.service.DummyEventQueue;
import fk.sp.sa.ingest.service.EventIngestionConfiguration;
import fk.sp.sa.ingest.storage.DruidStorageAdaptor;
import fk.sp.sa.ingest.storage.StorageAdaptor;

import java.io.IOException;

public class EventIngesterModule extends AbstractModule {

    private final EventIngestionConfiguration config;

    public EventIngesterModule(EventIngestionConfiguration config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        bind(StorageAdaptor.class).to(DruidStorageAdaptor.class);
        bind(EventIngestionConfiguration.class).toInstance(config);
    }


}
