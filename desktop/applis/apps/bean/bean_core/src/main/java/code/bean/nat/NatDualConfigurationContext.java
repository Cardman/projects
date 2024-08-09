package code.bean.nat;

import code.bean.nat.analyze.NatConfigurationCore;
import code.sml.Document;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

public class NatDualConfigurationContext {
    private String messagesFolder = "";

    private StringMap<String> properties = new StringMap<String>();

    private StringList addedFiles = new StringList();
    private StringList renderFiles = new StringList();

    public NatDualConfigurationContext() {
        setAddedFiles(new StringList());
        setRenderFiles(new StringList());
    }

    public static StringMap<Document> docs(StringMap<Document> _built, String _relative) {
        StringMap<Document> docs_ = new StringMap<Document>();
        for (EntryCust<String,Document> e: _built.entryList()) {
            docs_.addEntry(e.getKey().substring(_relative.length()),e.getValue());
        }
        return docs_;
    }

    public void init(NatConfigurationCore _conf) {
        _conf.updatePref();
        String firstUrl_ = _conf.getFirstUrl();
        getRenderFiles().removeAllString(firstUrl_);
        getRenderFiles().add(firstUrl_);
    }
    public static StringMap<String> files(NatDualConfigurationContext _d, StringMap<String> _other, String _rel){
        StringMap<String> files_ = new StringMap<String>();
        for (String a : _d.getAddedFiles()) {
            String val_ = _other.getVal(_rel + a);
            tryPut(files_, a, val_);
        }
        return files_;
    }

    private static void tryPut(StringMap<String> _files, String _key, String _val) {
        if (_val != null) {
            _files.put(_key, _val);
        }
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

}
