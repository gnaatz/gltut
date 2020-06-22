package logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides functions to retrieve loggers from everywhere.
 */
public class LoggerFactory {
    private static Map<String, Logger> logger = new HashMap<>();

    /**
     * Returns a logger which directly outputs to the console.
     * @return console logger
     */
    public static Logger getConsoleLogger() {
        String str = "Console";
        logger.computeIfAbsent(str, k -> createLogger(true));
        return logger.get(str);
    }

    /**
     * Returns a logger which outputs to the provided file.
     * @param path output file path
     * @return file logger to file "path"
     */
    public static Logger getFileLogger(String path) {
        logger.computeIfAbsent(path, k -> createLogger(false));
        return logger.get(path);
    }

    /**
     * Creates a logger.
     * @param instant determines if the output should be directly to console.
     * @return new Logger
     */
    private static Logger createLogger(boolean instant) {
        return new Logger(instant);
    }
}
