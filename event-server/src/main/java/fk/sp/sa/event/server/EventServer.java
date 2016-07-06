package fk.sp.sa.event.server;

import com.hubspot.dropwizard.guice.GuiceBundle;
import fk.sp.sa.event.guice.EventServerModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by rohit.k on 24/06/16.
 */
public class EventServer extends Application<EventServerConfiguration> {

    private GuiceBundle<EventServerConfiguration> guiceBundle;

    public static void main(String[] args) throws Exception {
        new EventServer().run(args);
    }


    @Override
    public void initialize(Bootstrap<EventServerConfiguration> bootstrap) {
        guiceBundle = GuiceBundle.<EventServerConfiguration>newBuilder()
                .addModule(new EventServerModule())
                .setConfigClass(EventServerConfiguration.class)
                .build();
        bootstrap.addBundle(guiceBundle);

    }

    @Override
    public void run(EventServerConfiguration configuration, Environment environment)
            throws Exception {
        environment.jersey().register(EventServerResource.class);
    }
}