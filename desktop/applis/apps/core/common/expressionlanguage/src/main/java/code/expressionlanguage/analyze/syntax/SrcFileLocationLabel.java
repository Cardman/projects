package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationLabel extends AbsSrcFileLocation {
    private final String label;
    private final FileBlock block;
    private final int index;

    public SrcFileLocationLabel(String _n, FileBlock _b, int _i) {
        label = _n;
        block = _b;
        index = _i;
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
        return new RowSrcLocation(EnSrcLocation.LABEL,getLabel(),getFileName(),getIndex());
    }

    public String getLabel() {
        return label;
    }
}
