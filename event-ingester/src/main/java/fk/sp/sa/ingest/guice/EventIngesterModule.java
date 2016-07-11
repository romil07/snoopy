package fk.sp.sa.ingest.guice;

import com.google.inject.AbstractModule;
import fk.sp.sa.ingest.service.EventIngestionConfiguration;
import fk.sp.sa.ingest.storage.DruidStorageAdaptor;
import fk.sp.sa.ingest.storage.StorageAdaptor;

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
