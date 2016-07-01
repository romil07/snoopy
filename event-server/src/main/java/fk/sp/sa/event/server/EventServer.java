package fk.sp.sa.event.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by rohit.k on 24/06/16.
 */
public class EventServer extends Application<EventServerConfiguration> {

    public static void main(String[] args) throws Exception {
        new EventServer().run(args);
    }


    @Override
    public void initialize(Bootstrap<EventServerConfiguration> bootstrap) {

    }
    @Override
    public void run(EventServerConfiguration configuration, Environment environment) throws Exception {

    }
}
