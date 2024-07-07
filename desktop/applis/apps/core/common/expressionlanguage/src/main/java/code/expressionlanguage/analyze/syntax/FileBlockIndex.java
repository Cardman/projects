package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;

public final class FileBlockIndex {
    private final FileBlockCursor cursor;
    private final SrcFileLocation callee;
    private final SrcFileLocation caller;

    public FileBlockIndex(FileBlock _f, int _i, SrcFileLocation _c, SrcFileLocation _a) {
        cursor = new FileBlockCursor(_f, _i);
        this.callee = _c;
        this.caller = _a;
    }

    public int getIndex() {
        return cursor.getIndex();
    }

    public FileBlock getFile() {
        return cursor.getFile();
    }

    public SrcFileLocation getCallee() {
        return callee;
    }

    public SrcFileLocation getCaller() {
        return caller;
    }
}
