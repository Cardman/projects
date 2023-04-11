package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;

public final class SrcFileLocationTypeVar implements SrcFileLocation {
    private final String name;
    private final int index;
    private final FileBlock block;

    public SrcFileLocationTypeVar(String _n, int _i, FileBlock _fileName) {
        this.name = _n;
        this.index = _i;
        this.block = _fileName;
    }

    @Override
    public String getFileName() {
        return block.getFileName();
    }

    @Override
    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

}
