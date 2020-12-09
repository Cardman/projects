package code.stream.core;

import code.util.StringMap;

public final class ReadFiles {
    private final StringMap<String> zipFiles;
    private final OutputType type;

    public ReadFiles(StringMap<String> _zipFiles, OutputType _type) {
        this.zipFiles = _zipFiles;
        this.type = _type;
    }

    public StringMap<String> getZipFiles() {
        return zipFiles;
    }

    public OutputType getType() {
        return type;
    }
}
