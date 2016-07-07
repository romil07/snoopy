package fk.sp.sa.transformer.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import fk.sp.sa.event.EventTransformer;
import fk.sp.sa.transformer.MetricsDBEventTransformer;

public class MetricsDBTransformerModule extends AbstractModule {

    public static final String EVENT_TYPE = "metricDB";

    @Override
    protected void configure() {
        MapBinder<String, EventTransformer> mapbinder =
                MapBinder.newMapBinder(binder(), String.class, EventTransformer.class);
        mapbinder.permitDuplicates();
        mapbinder.addBinding(EVENT_TYPE).toInstance(new MetricsDBEventTransformer());

    }
}
