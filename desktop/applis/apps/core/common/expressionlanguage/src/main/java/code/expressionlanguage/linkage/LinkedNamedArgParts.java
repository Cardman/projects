package code.expressionlanguage.linkage;

import code.expressionlanguage.analyze.blocks.FileBlock;

public final class LinkedNamedArgParts {
    private final FileBlock file;
    private final int offset;

    public LinkedNamedArgParts(FileBlock _f, int _o) {
        this.file = _f;
        this.offset = _o;
    }

    public FileBlock getFile() {
        return file;
    }

    public int getOffset() {
        return offset;
    }
}
