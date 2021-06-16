package code.expressionlanguage.utilcompo;

import code.threads.AbstractAtomicBoolean;
import code.util.StringList;
import code.util.StringMap;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ExecutingOptions {

    private String baseFiles="";
    private String srcFolder = "src";
    private String logFolder = "logs";
    private String mainThread = "main_thread.txt";
    private String files = "files";
    private String resources = "res";
    private String outputZip = "out/results.zip";
    private String outputFolder = "";
    private String output = "";
    private int tabWidth = 4;
    private final AbstractAtomicBoolean interrupt;
    private boolean covering;
    private String coverFolder = "coverage";
    private String errorsFolder = "errors";
    private boolean invokeDirect;
    private boolean hasArg;
    private StringList args = new StringList();
    private StringList warns = new StringList();
    private StringMap<String> messages = new StringMap<String>();
    private StringMap<String> keyWords = new StringMap<String>();
    private StringMap<String> aliases = new StringMap<String>();

    public ExecutingOptions(AbstractAtomicBoolean _inter) {
        interrupt = _inter;
    }
    public String getBaseFiles() {
        return baseFiles;
    }

    public void setBaseFiles(String _baseFiles) {
        baseFiles = _baseFiles;
    }

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

    public String getFiles() {
        return files;
    }

    public void setFiles(String _files) {
        this.files = _files;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String _resources) {
        this.resources = _resources;
    }

    public String getOutputZip() {
        return outputZip;
    }

    public void setOutputZip(String _outputZip) {
        this.outputZip = _outputZip;
    }

    public AbstractAtomicBoolean getInterrupt() {
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

    public String getErrorsFolder() {
        return errorsFolder;
    }

    public void setErrorsFolder(String _errorsFolder) {
        errorsFolder = _errorsFolder;
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

    public StringList getWarns() {
        return warns;
    }

    public void setWarns(StringList _warns) {
        this.warns = _warns;
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

    public void setMessages(StringMap<String> _messages) {
        this.messages = _messages;
    }

    public StringMap<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(StringMap<String> _keyWords) {
        keyWords = _keyWords;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String _output) {
        output = _output;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public void setOutputFolder(String _outputFolder) {
        outputFolder = _outputFolder;
    }
}
