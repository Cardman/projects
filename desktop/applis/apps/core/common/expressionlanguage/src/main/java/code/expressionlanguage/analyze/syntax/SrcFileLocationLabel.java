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
    public FileBlockCursor cursor() {
        return new FileBlockCursor(block,index);
    }

    public String getLabel() {
        return label;
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        FileBlockCursor cursor_ = cursor();
        return new RowSrcLocation(EnSrcLocation.LABEL,getLabel(),FileBlock.name(cursor_.getFile()), cursor_.getIndex());
    }
}
