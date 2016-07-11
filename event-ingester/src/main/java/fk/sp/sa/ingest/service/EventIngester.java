package fk.sp.sa.ingest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Optional;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import fk.sp.sa.event.Message;
import fk.sp.sa.event.EventConsumer;
import fk.sp.sa.ingest.guice.EventIngesterModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EventIngester {

    private EventConsumer eventConsumer;
    private EventHandler eventHandler;
    private volatile boolean stop;

    @Inject
    public EventIngester(EventConsumer eventConsumer, EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.eventConsumer = eventConsumer;
    }

    public void init() {
    }

    public void start() {
        while (!stop) {
            Message message = eventConsumer.getNext();
            eventHandler.handleEvent(message.getInputEvent());
        }
    }

    public void stop() {
        stop = true;
    }

    public static Options getOptions() {
        Options options = new Options();
        //options.addOption(
          //      Option.builder("c").required().longOpt("config").hasArg().desc("Path of config file").build());
        return options;
    }

    private static EventIngestionConfiguration readConfig(String configPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        File configFile = new File(configPath);
        return objectMapper.readValue(configFile, EventIngestionConfiguration.class);
    }

    private static List<Optional<Module>> getTransformerModules(EventIngestionConfiguration configuration) {
        System.out.println("Type of transfomers "+configuration.getTransformers());
        List<String> transformerModules = configuration.getTransformers();
        return transformerModules.parallelStream().map(transformerModule -> {
            try {
                Class clazz = Class.forName(transformerModule);
                return Optional.of((Module) clazz.newInstance());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return Optional.<Module>absent();
        }).collect(Collectors.toList());

    }

    public static void main(String[] args) throws IOException {
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(getOptions(), args);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(EventIngester.class.getCanonicalName(), getOptions());
            return;
        }
        String configPath = commandLine.getOptionValue("config");
        EventIngestionConfiguration configuration = readConfig(configPath);
        List<Module> modules = getTransformerModules(configuration).stream().
                filter(moduleOptional -> moduleOptional.isPresent()).map(moduleOptional -> moduleOptional.get()).
                collect(Collectors.toList());
        modules.add(new EventIngesterModule(configuration));
        Injector injector = Guice.createInjector(modules);
        EventIngester eventIngester = injector.getInstance(EventIngester.class);
        log.info("Starting the event ingester");
        eventIngester.start();
        log.info("Event Ingester started");
    }
}
