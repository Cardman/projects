package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;

public final class FileBlockCursor {
    private final FileBlock file;
    private final int index;

    public FileBlockCursor(FileBlock _f, int _i) {
        this.file = _f;
        this.index = _i;
    }

    public int getIndex() {
        return index;
    }

    public FileBlock getFile() {
        return file;
    }
}
