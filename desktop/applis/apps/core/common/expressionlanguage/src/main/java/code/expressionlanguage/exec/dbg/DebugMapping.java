package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.exec.blocks.ExecFileBlock;
import code.util.IdMap;

public final class DebugMapping {
    private final IdMap<FileBlock, ExecFileBlock> files = new IdMap<FileBlock, ExecFileBlock>();
    private final BreakPointBlockList breakPointsBlock = new BreakPointBlockList();
    private final boolean debugging;

    public DebugMapping(boolean _d) {
        this.debugging = _d;
    }

    public void addFile(FileBlock _file, ExecFileBlock _e) {
        if (!debugging) {
            return;
        }
        files.addEntry(_file, _e);
    }
    public BreakPointBlockList getBreakPointsBlock() {
        return breakPointsBlock;
    }

    public IdMap<FileBlock, ExecFileBlock> getFiles() {
        return files;
    }
}
