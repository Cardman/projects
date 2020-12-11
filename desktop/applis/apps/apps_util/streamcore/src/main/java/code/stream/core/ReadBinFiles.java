package code.stream.core;

import code.util.StringMap;

public final class ReadBinFiles {
    private final StringMap<ContentTime> zipFiles;
    private final StringMap<ContentTime> zipFolders;
    private final OutputType type;

    public ReadBinFiles(StringMap<ContentTime> _zipFiles, StringMap<ContentTime> _zipFolders, OutputType _type) {
        this.zipFiles = _zipFiles;
        zipFolders = _zipFolders;
        this.type = _type;
    }

    public StringMap<ContentTime> getZipFiles() {
        return zipFiles;
    }

    public StringMap<ContentTime> getZipFolders() {
        return zipFolders;
    }

    public OutputType getType() {
        return type;
    }
}
