package logger;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    private List<Log> logs;
    private boolean instantOutput;

    Logger(boolean instantOutput) {
        logs = new ArrayList<>();
        this.instantOutput = instantOutput;
    }

    private void log(Tag tag, String msg, Severity severity) {
        Log current = new Log(tag, msg, severity);
        logs.add(current);
        if(instantOutput) {
            System.out.println(severity.getColor()
            + severity.toString() + ":" + tag + ":" + (current.getTime() / 1000) + "> " + msg + "\u001b[0m");
        } else {
            //TODO: print to some log file
        }
    }

    public void info(Tag tag, String msg) {
        log(tag, msg, Severity.INFO);
    }

    public void debug(Tag tag, String msg) {
        log(tag, msg, Severity.DEBUG);
    }

    public void error(Tag tag, String msg) {
        log(tag, msg, Severity.ERROR);
    }

    public void warning(Tag tag, String msg) {
        log(tag, msg, Severity.WARNING);
    }

    public void fatal(Tag tag, String msg) {
        log(tag, msg, Severity.FATAL);
    }
}
