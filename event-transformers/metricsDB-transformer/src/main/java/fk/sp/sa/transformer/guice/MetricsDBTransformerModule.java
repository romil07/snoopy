package fk.sp.sa.transformer.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;

import fk.sp.sa.transformer.MetricsDBEventTransformer;
import fk.sp.sa.event.EventTransformer;

public class MetricsDBTransformerModule extends AbstractModule {

  public static final String TRANSFORMER_NAME="metricDB";
  @Override
  protected void configure() {
    MapBinder<String, EventTransformer> mapbinder =
        MapBinder.newMapBinder(binder(), String.class, EventTransformer.class);
    mapbinder.addBinding(TRANSFORMER_NAME).toInstance(new MetricsDBEventTransformer());

  }
}
