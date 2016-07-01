package fk.sp.sa.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RoutingConfiguration {
    private Map<String,List<String>> routes;

    public List<String> getRoutes(String outputEventName){
        return routes.get(outputEventName);
    }
}
