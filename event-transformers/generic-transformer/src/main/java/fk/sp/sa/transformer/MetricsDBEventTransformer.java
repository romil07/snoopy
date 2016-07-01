package fk.sp.sa.transformer;

import fk.sp.sa.event.EventTransformer;
import fk.sp.sa.event.input.InputEvent;
import fk.sp.sa.event.output.BasicEvent;
import fk.sp.sa.event.output.OutputEvent;

public class MetricsDBEventTransformer implements EventTransformer {
    @Override
    public BasicEvent transform(InputEvent inputEvent) {
        return null;
    }

    @Override
    public String getInputEventType() {
        return null;
    }
}
