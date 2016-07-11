package fk.sp.sa.event;

import fk.sp.sa.event.Message;

/**
 * Created by romil.goyal on 11/07/16.
 */
public interface EventConsumer {

    public Message getNext();

    public void init();

    public void reset();

    public void reset(int offset);
}
