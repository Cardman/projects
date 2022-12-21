package code.expressionlanguage.utilcompo;

import code.stream.core.ContentTime;
import code.util.StringMap;

public final class ExportedFolder {
    private final StringMap<ContentTime> out;
    private final StringMap<FolderStruct> folders;

    public ExportedFolder(StringMap<ContentTime> _out, StringMap<FolderStruct> _folders) {
        this.out = _out;
        this.folders = _folders;
    }

    public StringMap<ContentTime> getOut() {
        return out;
    }

    public StringMap<FolderStruct> getFolders() {
        return folders;
    }
}
