package fk.sp.sa.event.output;

import lombok.Data;
import lombok.experimental.Builder;

/**
 * Created by rohit.k on 24/06/16.
 */
@Data
@Builder
public class MetricDBOutputEvent extends OutputEvent {

    private final static String EVENT_NAME = "metricDB";
    @Dimension
    private String userId;
    @Dimension
    private String sellerId;
    @Dimension
    private String channel;
    @Dimension
    private String org;
    @Time
    private long timestamp;
    @Dimension
    private String action;
    @Dimension
    private String name;
    @Dimension
    private String category;
    @Dimension
    private String label;
    @Measure(aggregators = "sum")
    private float measure;
    @Measure(aggregators = "hyperUnique")
    private String sessionId;

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }


}
