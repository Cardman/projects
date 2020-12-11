package code.stream.core;

import code.util.StringList;
import code.util.StringMap;

public final class ReadBinFiles {
    private final StringMap<ContentTime> zipFiles;
    private final StringMap<ContentTime> zipFolders;
    private final StringList folders;
    private final OutputType type;

    public ReadBinFiles(StringMap<ContentTime> _zipFiles, StringMap<ContentTime> _zipFolders,StringList _folders, OutputType _type) {
        this.zipFiles = _zipFiles;
        zipFolders = _zipFolders;
        folders = _folders;
        this.type = _type;
    }

    public StringMap<ContentTime> getZipFiles() {
        return zipFiles;
    }

    public StringMap<ContentTime> getZipFolders() {
        return zipFolders;
    }

    public StringList getFolders() {
        return folders;
    }

    public OutputType getType() {
        return type;
    }
}
