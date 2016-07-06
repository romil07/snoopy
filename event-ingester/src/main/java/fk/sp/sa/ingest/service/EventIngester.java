package fk.sp.sa.ingest.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import fk.sp.sa.event.EventQueue;
import fk.sp.sa.event.Message;
import fk.sp.sa.ingest.guice.EventIngesterModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

@Slf4j
public class EventIngester {

    private EventQueue eventQueue;
    private EventHandler eventHandler;
    private volatile boolean stop;

    public EventIngester(EventQueue eventQueue, EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.eventQueue = eventQueue;
    }

    public void init() {
    }

    public void start() {
        while (!stop) {
            Message message = eventQueue.getNext();
            eventHandler.handleEvent(message.getInputEvent());
        }
    }

    public void stop() {
        stop = true;
    }

    public static Options getOptions() {
        Options options = new Options();
        options.addOption(
                Option.builder().argName("config").hasArg().desc("Path of config file").build());
        return options;
    }

    public static void main(String[] args) {
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(getOptions(), args);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("ingester", getOptions());
            return;
        }
        String configPath = commandLine.getOptionValue("config");
        Injector injector = Guice.createInjector(new EventIngesterModule(configPath));
        EventIngester eventIngester = injector.getInstance(EventIngester.class);
        log.info("Starting the event ingester");
        eventIngester.start();
        log.info("Event Ingester started");
    }
}
