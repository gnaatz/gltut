package logger;

import java.lang.management.ManagementFactory;

public class Log {
    private Tag tag;
    private String msg;
    private long time;
    private Severity severity;

    Log(Tag tag, String msg, Severity severity) {
        this.tag = tag;
        this.msg = msg;
        this.severity = severity;
        this.time = ManagementFactory.getRuntimeMXBean().getUptime();;
    }

    public String getMsg() {
        return msg;
    }

    public Tag getTag() {
        return tag;
    }

    long getTime() {
        return time;
    }

    public Severity getSeverity() {
        return severity;
    }
}
