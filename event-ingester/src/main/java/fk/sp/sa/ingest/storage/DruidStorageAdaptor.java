package fk.sp.sa.ingest.storage;

import com.google.inject.Inject;
import fk.sp.sa.event.output.OutputEvent;
import fk.sp.sa.ingest.service.EventIngestionConfiguration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DruidStorageAdaptor implements StorageAdaptor {

    @Inject
    public DruidStorageAdaptor(EventIngestionConfiguration eventIngestionConfiguration) {

    }

    @Override
    public void init() {
    }

    @Override
    public void push(OutputEvent outputEvent) {
        log.debug("Pushing to druid;event " + outputEvent);
    }

    @Override
    public void close() {

    }
}
