package code.formathtml.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.structs.ValidatorInfo;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class DualConfigurationContext {

    private static final String EMPTY_STRING = "";

    private RendAnalysisMessages analysisMessages = new RendAnalysisMessages();
    private AbstractFileBuilder fileBuilder;
    private String messagesFolder = EMPTY_STRING;

    private StringMap<String> properties = new StringMap<String>();

    private StringList addedFiles = new StringList();
    private StringList renderFiles = new StringList();

    private StringMap<ValidatorInfo> lateValidators;
    private String filesConfName = "";
    private ContextEl context;

    public RendAnalysisMessages getAnalysisMessages() {
        return analysisMessages;
    }

    public AbstractFileBuilder getFileBuilder() {
        return fileBuilder;
    }

    public void setFileBuilder(AbstractFileBuilder _fileBuilder) {
        this.fileBuilder = _fileBuilder;
    }

    public String getMessagesFolder() {
        return messagesFolder;
    }

    public void setMessagesFolder(String _messagesFolder) {
        this.messagesFolder = _messagesFolder;
    }

    public StringMap<String> getProperties() {
        return properties;
    }

    public void setProperties(StringMap<String> _properties) {
        this.properties = _properties;
    }

    public StringList getAddedFiles() {
        return addedFiles;
    }

    public void setAddedFiles(StringList _addedFiles) {
        this.addedFiles = _addedFiles;
    }

    public StringList getRenderFiles() {
        return renderFiles;
    }

    public void setRenderFiles(StringList _renderFiles) {
        this.renderFiles = _renderFiles;
    }

    public StringMap<ValidatorInfo> getLateValidators() {
        return lateValidators;
    }

    public void setupLateValidators(StringMap<String> _lateValidators) {
        StringMap<ValidatorInfo> lateValidators_ = new StringMap<ValidatorInfo>();
        for (EntryCust<String, String> e: _lateValidators.entryList()) {
            ValidatorInfo val_ = new ValidatorInfo();
            val_.setClassName(e.getValue());
            lateValidators_.addEntry(e.getKey(), val_);
        }
        setLateValidators(lateValidators_);
    }

    public void setLateValidators(StringMap<ValidatorInfo> _lateValidators) {
        this.lateValidators = _lateValidators;
    }

    public String getFilesConfName() {
        return filesConfName;
    }

    public void setFilesConfName(String _filesConfName) {
        this.filesConfName = _filesConfName;
    }

    public ContextEl getContext() {
        return context;
    }

    public void setContext(ContextEl _context) {
        this.context = _context;
    }

}
