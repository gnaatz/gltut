package logger;

public enum Severity {
    WTF("\u001B[36m"),
    FATAL("\u001B[35m"),
    ERROR("\u001B[31m"),
    WARNING("\u001B[32m"),
    DEBUG("\u001B[33m"),
    INFO("\u001B[37m");

    private String color;

    Severity(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
