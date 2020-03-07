package code.expressionlanguage.utilcompo;

import code.util.StringList;
import code.util.StringMap;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ExecutingOptions {

    private String srcFolder = "src";
    private String logFolder = "logs";
    private String mainThread = "main_thread.txt";
    private int tabWidth = 4;
    private AtomicBoolean interrupt = new AtomicBoolean();
    private boolean covering;
    private String coverFolder = "coverage";
    private boolean invokeDirect;
    private boolean hasArg;
    private StringList args = new StringList();
    private StringMap<String> messages = new StringMap<String>();
    private StringMap<String> keyWords = new StringMap<String>();
    private StringMap<String> aliases = new StringMap<String>();

    public String getSrcFolder() {
        return srcFolder;
    }

    public void setSrcFolder(String _srcFolder) {
        srcFolder = _srcFolder;
    }

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

    public AtomicBoolean getInterrupt() {
        return interrupt;
    }

    public boolean isCovering() {
        return covering;
    }

    public void setCovering(boolean _covering) {
        covering = _covering;
    }

    public String getCoverFolder() {
        return coverFolder;
    }

    public void setCoverFolder(String _coverFolder) {
        coverFolder = _coverFolder;
    }

    public int getTabWidth() {
        return tabWidth;
    }

    public void setTabWidth(int _tabWidth) {
        tabWidth = _tabWidth;
    }

    public boolean isInvokeDirect() {
        return invokeDirect;
    }

    public void setInvokeDirect(boolean _invokeDirect) {
        invokeDirect = _invokeDirect;
    }

    public boolean isHasArg() {
        return hasArg;
    }

    public void setHasArg(boolean _hasArg) {
        hasArg = _hasArg;
    }

    public StringList getArgs() {
        return args;
    }

    public void setArgs(StringList _args) {
        args = _args;
    }

    public StringMap<String> getAliases() {
        return aliases;
    }

    public void setAliases(StringMap<String> _aliases) {
        aliases = _aliases;
    }

    public StringMap<String> getMessages() {
        return messages;
    }

    public void setMessages(StringMap<String> messages) {
        this.messages = messages;
    }

    public StringMap<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(StringMap<String> _keyWords) {
        keyWords = _keyWords;
    }
}
