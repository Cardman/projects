package code.formathtml.analyze;

import code.formathtml.Configuration;
import code.formathtml.errors.RendKeyWords;
import code.util.StringList;
import code.util.StringMap;

public abstract class SetupableAnalyzingDoc {
    private StringList languages = new StringList();

    private String internGlobalClass="";
    private RendKeyWords rendKeyWords = new RendKeyWords();

    private int nextIndex;
    private String prefix = "";

    private StringMap<String> properties = new StringMap<String>();

    private String messagesFolder = "";
    private StringMap<String> files = new StringMap<String>();

    protected SetupableAnalyzingDoc() {
    }
    public abstract void setup(Configuration _conf, StringMap<String> _properties, String _messagesFolder);
    protected void setupCommon(Configuration _conf, StringMap<String> _properties, String _messagesFolder) {
        rendKeyWords = _conf.getRendKeyWords();
        prefix = _conf.getPrefix();
        setProperties(_properties);
        setMessagesFolder(_messagesFolder);
        files = _conf.getFiles();
    }
    public void setProperties(StringMap<String> _p) {
        this.properties = _p;
    }

    public void setMessagesFolder(String _m) {
        this.messagesFolder = _m;
    }

    public boolean isInternGlobal() {
        return !getInternGlobalClass().isEmpty();
    }

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _languages) {
        languages = _languages;
    }

    public String getInternGlobalClass() {
        return internGlobalClass;
    }

    public void setInternGlobalClass(String _internGlobalClass) {
        internGlobalClass = _internGlobalClass;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        this.nextIndex = _nextIndex;
    }

    public RendKeyWords getRendKeyWords() {
        return rendKeyWords;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getMessagesFolder() {
        return messagesFolder;
    }

    public StringMap<String> getProperties() {
        return properties;
    }

    public StringMap<String> getFiles() {
        return files;
    }
}
