package fk.sp.sa.event.output;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Slf4j
@ToString
public abstract class OutputEvent {

    /*
    This method provides a name of the event which is used to read the route of this event from config files
     */
    public abstract String getEventName();

    /*
    It provides a set of fields which are to be used as dimensions during analytics
     */
    public Set<String> getDimensions() {
        Set<String> dimensions = new HashSet<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Dimension.class)) {
                dimensions.add(field.getName());
            }
        }
        return dimensions;
    }

    /*
    It provides a Map of aggregation fields to list of aggregation types
     */
    public Map<String, String[]> getMeasures() {
        Map<String, String[]> measures = new HashMap<>();
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Measure.class)) {
                String[] aggregators = field.getAnnotation(Measure.class).aggregators();
                if (aggregators != null) {
                    measures.put(field.getName(), aggregators);
                } else {
                    log.warn(
                            "Aggregators not defined for measure " + field.getName() + " .Ignoring this measure");
                }
            }
        }
        return measures;
    }

    public String getTimestampField() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Time.class)) {
                return field.getName();
            }
        }
        return null;
    }


}
