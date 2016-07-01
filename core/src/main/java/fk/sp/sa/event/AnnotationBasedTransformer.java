package fk.sp.sa.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import fk.sp.sa.event.input.InputEvent;
import fk.sp.sa.event.output.OutputEvent;

import java.util.Map;

public abstract class AnnotationBasedTransformer<T extends InputEvent> implements EventTransformer {
    final private Class<T> clazz;
    final private ObjectMapper mapper;
    public AnnotationBasedTransformer(Class<T> clazz,ObjectMapper mapper){
        this.clazz = clazz;
        this.mapper = mapper;
    }
    @Override
    public OutputEvent transform(InputEvent inputEvent) {
        return null;
    }

    public InputEvent convertToInputEvent(Map<String,Object> map){
        T inputEvent = mapper.convertValue(map,clazz);
        return inputEvent;
    }

}
