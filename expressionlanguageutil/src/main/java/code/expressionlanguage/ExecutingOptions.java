package code.expressionlanguage;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ExecutingOptions {

    private String logFolder = "logs";
    private String mainThread = "main_thread.txt";
    private int tabWidth = 4;
    private AtomicBoolean interrupt = new AtomicBoolean();
    public String getLogFolder() {
        return logFolder;
    }

    public String getMainThread() {
        return mainThread;
    }

    public AtomicBoolean getInterrupt() {
        return interrupt;
    }

}
