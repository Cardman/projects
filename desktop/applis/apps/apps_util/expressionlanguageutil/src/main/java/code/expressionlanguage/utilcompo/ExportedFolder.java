package code.expressionlanguage.utilcompo;

import code.util.StringMap;

public final class ExportedFolder {
    private final StringMap<byte[]> out;
    private final StringMap<FolderStruct> folders;

    public ExportedFolder(StringMap<byte[]> _out, StringMap<FolderStruct> _folders) {
        this.out = _out;
        this.folders = _folders;
    }

    public StringMap<byte[]> getOut() {
        return out;
    }

    public StringMap<FolderStruct> getFolders() {
        return folders;
    }
}
