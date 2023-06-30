package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.*;
import code.util.*;

public final class FileResultMem {
    private final FolderStruct folder;
    private final String simpleName;
    public FileResultMem(MemoryFileSystem _mem, String _file, String _rCont) {
        String abs_ = _mem.absolutePath(_file, _rCont);
        StringList parts_ = PathUtil.splitParts(abs_);
        FolderStruct curr_ = _mem.getParentFolder(parts_);
        if (curr_ == null) {
            folder = null;
            simpleName = "";
            return;
        }
        simpleName = parts_.last();
        if (!_mem.getNameValidating().ok(simpleName)) {
            folder = null;
            return;
        }
        folder = curr_;
    }

    public FolderStruct getFolder() {
        return folder;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
