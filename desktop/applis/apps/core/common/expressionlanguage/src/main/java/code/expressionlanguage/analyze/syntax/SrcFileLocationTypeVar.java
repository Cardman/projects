package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;

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
    public FileBlock getFile() {
        return block;
    }

    @Override
    public String getFileName() {
        return FileBlock.name(getFile());
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.TYPE_VAR,getName(),getFileName(),getIndex());
    }

    public String getName() {
        return name;
    }

}
