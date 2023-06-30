package code.expressionlanguage.utilcompo;

import code.expressionlanguage.filenames.*;
import code.util.*;

public final class FolderResultMem {
    private final FolderStruct current;
    private final int delta;
    private final String simpleName;
    public FolderResultMem(MemoryFileSystem _mem, String _file, String _rCont) {
        String abs_ = _mem.absolutePath(_file, _rCont);
        delta = MemoryFileSystem.delta(MemoryFileSystem.endsSep(abs_));
        CustList<String> parts_ = PathUtil.splitParts(abs_);
        current = _mem.getParentFolder(parts_.left(parts_.size()+delta));
        simpleName = parts_.get(parts_.size() - 1 + delta);
    }

    public FolderStruct getCurrent() {
        return current;
    }

    public int getDelta() {
        return delta;
    }

    public String getSimpleName() {
        return simpleName;
    }
}
