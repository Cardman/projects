package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;

public final class FileBlockIndex {
    private final FileBlock file;
    private final int index;
    private final SrcFileLocation callee;

    public FileBlockIndex(FileBlock _f, int _i, SrcFileLocation _c) {
        this.file = _f;
        this.index = _i;
        this.callee = _c;
    }

    public int getIndex() {
        return index;
    }

    public FileBlock getFile() {
        return file;
    }

    public SrcFileLocation getCallee() {
        return callee;
    }
}
