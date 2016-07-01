package fk.sp.sa.event.server;

import com.fasterxml.jackson.annotation.JsonProperty;
import fk.sp.sa.event.RoutingConfiguration;
import io.dropwizard.Configuration;
import lombok.Data;

@Data
public class EventServerConfiguration extends Configuration{
    @JsonProperty("routes")
    private RoutingConfiguration routingConfiguration;

}
