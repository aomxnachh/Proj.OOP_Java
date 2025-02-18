package main.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(String.format("[%s] %s", 
            LocalDateTime.now().format(formatter), message));
    }

    public void error(String message, Exception e) {
        System.err.println(String.format("[%s] ERROR: %s - %s", 
            LocalDateTime.now().format(formatter), message, e.getMessage()));
    }
}