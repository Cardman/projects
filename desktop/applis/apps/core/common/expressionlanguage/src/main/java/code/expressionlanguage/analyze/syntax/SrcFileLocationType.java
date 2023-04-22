package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.DisplayedStrings;

public final class SrcFileLocationType implements SrcFileLocation {
    private final RootBlock type;

    public SrcFileLocationType(RootBlock _t) {
        this.type = _t;
    }

    @Override
    public String getFileName() {
        return getType().getFile().getFileName();
    }

    @Override
    public int getIndex() {
        return getType().getIdRowCol();
    }

    @Override
    public RowSrcLocation build(DisplayedStrings _dis) {
        return new RowSrcLocation(EnSrcLocation.TYPE,getType().getFullName(),getFileName(),getIndex());
    }

    public RootBlock getType() {
        return type;
    }
}
