package code.expressionlanguage.options;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ExecutingOptions {

    private String logFolder = "logs";
    private String mainThread = "main_thread.txt";
    private int tabWidth = 4;
    private AtomicBoolean interrupt = new AtomicBoolean();
    public String getLogFolder() {
        return logFolder;
    }
    public void setLogFolder(String _logFolder) {
        logFolder = _logFolder;
    }
    public String getMainThread() {
        return mainThread;
    }
    public void setMainThread(String _mainThread) {
        mainThread = _mainThread;
    }
    public int getTabWidth() {
        return tabWidth;
    }
    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }
    public AtomicBoolean getInterrupt() {
        return interrupt;
    }
    public void setInterrupt(AtomicBoolean _interrupt) {
        interrupt = _interrupt;
    }

}
