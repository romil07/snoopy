package fk.sp.sa.event.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * Created by rohit.k on 24/06/16.
 */
@Data
public class InputEvent {

    protected long timestamp;
    @JsonProperty("type")
    protected String eventType;
    @JsonProperty("event")
    protected Map<String, Object> eventPayload;
}
