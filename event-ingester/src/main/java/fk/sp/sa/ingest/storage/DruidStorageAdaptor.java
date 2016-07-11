package fk.sp.sa.ingest.storage;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.metamx.tranquility.config.DataSourceConfig;
import com.metamx.tranquility.config.PropertiesBasedConfig;
import com.metamx.tranquility.config.TranquilityConfig;
import com.metamx.tranquility.druid.DruidBeams;
import com.metamx.tranquility.tranquilizer.MessageDroppedException;
import com.metamx.tranquility.tranquilizer.Tranquilizer;
import com.twitter.util.FutureEventListener;
import fk.sp.sa.event.output.OutputEvent;
import fk.sp.sa.ingest.service.EventIngestionConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import scala.runtime.BoxedUnit;

import java.io.InputStream;
import java.util.Map;

@Slf4j
public class DruidStorageAdaptor implements StorageAdaptor {

    @Inject
    public DruidStorageAdaptor(EventIngestionConfiguration eventIngestionConfiguration) {

    }

    @Override
    public void init() {
    }

    @Override
    public void push(OutputEvent outputEvent) {
        final InputStream configStream = DruidStorageAdaptor.class.getClassLoader().getResourceAsStream("example.json");
        final TranquilityConfig<PropertiesBasedConfig> config = TranquilityConfig.read(configStream);
        final DataSourceConfig<PropertiesBasedConfig> wikipediaConfig = config.getDataSource("wikipedia");
        final Tranquilizer<Map<String, Object>> sender = DruidBeams.fromConfig(wikipediaConfig)
                .buildTranquilizer(wikipediaConfig.tranquilizerBuilder());

        sender.start();

        try {
            // Send 10000 objects

            for (int i = 0; i < 10000; i++) {
                // Build a sample event to send; make sure we use a current date
                final Map<String, Object> obj = ImmutableMap.<String, Object>of(
                        "timestamp", new DateTime().toString(),
                        "page", "foo",
                        "added", i
                );

                // Asynchronously send event to Druid:
                sender.send(obj).addEventListener(
                        new FutureEventListener<BoxedUnit>() {
                            @Override
                            public void onSuccess(BoxedUnit value) {
                                log.info("Sent message: %s", obj);
                            }

                            @Override
                            public void onFailure(Throwable e) {
                                if (e instanceof MessageDroppedException) {
                                    log.warn("Dropped message: %s", obj);
                                } else {
                                    log.error("Failed to send message: %s", obj);
                                }
                            }
                        }
                );
            }
        } finally {
            sender.flush();
            sender.stop();
        }
        log.debug("Pushing to druid;event " + outputEvent);
    }

    @Override
    public void close() {

    }
}
