package code.formathtml.util;

import code.expressionlanguage.analyze.AbstractFileBuilder;
import code.expressionlanguage.options.Options;
import code.formathtml.errors.RendAnalysisMessages;
import code.formathtml.structs.ValidatorInfo;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public final class DualConfigurationContext {

    private static final String EMPTY_STRING = "";

    private final RendAnalysisMessages analysisMessages = new RendAnalysisMessages();
    private AbstractFileBuilder fileBuilder;
    private String messagesFolder = EMPTY_STRING;

    private StringMap<String> properties = new StringMap<String>();
    private StringList addedResources = new StringList();

    private StringList addedFiles = new StringList();
    private StringList renderFiles = new StringList();

    private StringMap<ValidatorInfo> lateValidators;
    private String filesConfName = "";
    private boolean ko;
    private final Options options = new Options();

    public void feedRenders(StringMap<String> _rend, String _prefix) {
        for (String f: _rend.getKeys()) {
            if (!f.startsWith(_prefix)) {
                continue;
            }
            getRenderFiles().add(f);
        }
    }
    public Options getOptions() {
        return options;
    }

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

    public StringList getAddedResources() {
        return addedResources;
    }

    public void setAddedResources(StringList _addedResources) {
        this.addedResources = _addedResources;
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

    public boolean isKo() {
        return ko;
    }

    public void setKo(boolean _ko) {
        this.ko = _ko;
    }
}
