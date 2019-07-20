package code.formathtml.util;

import code.formathtml.RendBlock;
import code.util.StringList;
import code.util.StringMap;

public final class AnalyzingDoc {
    private StringMap<String> files = new StringMap<String>();
    private StringList languages = new StringList();
    private String language = "";
    private String[] resourcesFolder;

    private RendBlock currentBlock;
    private String internGlobalClass="";

    public void setFiles(StringMap<String> _files) {
        files = _files;
    }

    public StringMap<String> getFiles() {
        return files;
    }

    public StringList getLanguages() {
        return languages;
    }

    public void setLanguages(StringList _languages) {
        languages = _languages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String _language) {
        language = _language;
    }

    public String[] getResourcesFolder() {
        return resourcesFolder;
    }

    public void setResourcesFolder(String[] _resourcesFolder) {
        resourcesFolder = _resourcesFolder;
    }

    public RendBlock getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(RendBlock _currentBlock) {
        currentBlock = _currentBlock;
    }

    public String getInternGlobalClass() {
        return internGlobalClass;
    }

    public void setInternGlobalClass(String _internGlobalClass) {
        internGlobalClass = _internGlobalClass;
    }
}
