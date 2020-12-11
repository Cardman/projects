package code.stream.core;

import code.util.StringList;
import code.util.StringMap;

public final class ReadFiles {
    private final StringMap<String> zipFiles;
    private final StringList folders;
    private final OutputType type;

    public ReadFiles(StringMap<String> _zipFiles, StringList _folders,OutputType _type) {
        this.zipFiles = _zipFiles;
        folders = _folders;
        this.type = _type;
    }

    public StringMap<String> getZipFiles() {
        return zipFiles;
    }

    public StringList getFolders() {
        return folders;
    }

    public OutputType getType() {
        return type;
    }
}
