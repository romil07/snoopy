package fk.sp.sa.event;

public interface EventQueue {

    public Message getNext();

    public void init();

    public void reset();

    public void reset(int offset);

    public void put(Message message);
}
