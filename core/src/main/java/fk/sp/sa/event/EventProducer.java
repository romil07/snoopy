package fk.sp.sa.event;

import fk.sp.sa.event.Message;

public interface EventProducer {

    public void init();

    public void put(Message message);
}
