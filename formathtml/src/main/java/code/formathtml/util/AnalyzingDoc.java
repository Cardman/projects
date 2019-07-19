package code.formathtml.util;

import code.util.StringList;
import code.util.StringMap;

public final class AnalyzingDoc {
    private StringMap<String> files = new StringMap<String>();
    private StringList languages = new StringList();
    private String language = "";
    private String[] resourcesFolder;

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
}
