package code.expressionlanguage.utilcompo;

import code.expressionlanguage.analyze.MethodHeaders;
import code.expressionlanguage.analyze.ReportedMessages;
import code.expressionlanguage.files.CommentDelimiters;
import code.util.CustList;
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
    private boolean errors;
    private String coverFolder = "coverage";
    private String errorsFolder = "errors";
    private boolean invokeDirect;
    private boolean hasArg;
    private StringList args = new StringList();
    private StringList typesInit = new StringList();
    private StringMap<String> messages = new StringMap<String>();
    private StringMap<String> keyWords = new StringMap<String>();
    private StringMap<String> aliases = new StringMap<String>();
    private CustList<CommentDelimiters> comments = new CustList<CommentDelimiters>();
    private ReportedMessages methodHeaders = new ReportedMessages();

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

    public boolean isErrors() {
        return errors;
    }

    public void setErrors(boolean _errors) {
        errors = _errors;
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

    public StringList getTypesInit() {
        return typesInit;
    }

    public void setTypesInit(StringList _typesInit) {
        typesInit = _typesInit;
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

    public CustList<CommentDelimiters> getComments() {
        return comments;
    }

    public void setComments(CustList<CommentDelimiters> comments) {
        this.comments = comments;
    }

    public ReportedMessages getMethodHeaders() {
        return methodHeaders;
    }

    public void setMethodHeaders(ReportedMessages methodHeaders) {
        this.methodHeaders = methodHeaders;
    }
}
