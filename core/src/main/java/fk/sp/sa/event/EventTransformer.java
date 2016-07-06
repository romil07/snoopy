package fk.sp.sa.event;

import fk.sp.sa.event.input.InputEvent;
import fk.sp.sa.event.output.OutputEvent;

public interface EventTransformer {

    public OutputEvent transform(InputEvent inputEvent);

    public String getInputEventType();
}
