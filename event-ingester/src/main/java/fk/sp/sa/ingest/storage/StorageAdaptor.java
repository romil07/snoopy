package fk.sp.sa.ingest.storage;

import fk.sp.sa.event.output.OutputEvent;

public interface StorageAdaptor {
    public void init();
    public void push(OutputEvent outputEvent);
    public void close();
}
