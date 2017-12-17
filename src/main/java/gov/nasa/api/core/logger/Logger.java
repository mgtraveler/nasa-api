package gov.nasa.api.core.logger;

import org.slf4j.LoggerFactory;

public final class Logger {

    public static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Logger.class.getName());

    private Logger() {
    }
}