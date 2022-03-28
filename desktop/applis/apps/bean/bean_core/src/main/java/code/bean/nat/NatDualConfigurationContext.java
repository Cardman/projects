package code.bean.nat;

import code.formathtml.Configuration;
import code.util.StringList;
import code.util.StringMap;

public class NatDualConfigurationContext {
    private String messagesFolder = "";

    private StringMap<String> properties = new StringMap<String>();

    private StringList addedFiles = new StringList();
    private StringList renderFiles = new StringList();

    private StringMap<StringMap<String>> navigation = new StringMap<StringMap<String>>();

    public NatDualConfigurationContext() {
        setAddedFiles(new StringList());
        setRenderFiles(new StringList());
    }
    public void init(Configuration _conf) {
        _conf.updatePref();
        String firstUrl_ = _conf.getFirstUrl();
        getRenderFiles().removeAllString(firstUrl_);
        getRenderFiles().add(firstUrl_);
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

    public StringMap<StringMap<String>> getNavigation() {
        return navigation;
    }

    public void setNavigation(StringMap<StringMap<String>> _navigation) {
        navigation = _navigation;
    }

}
