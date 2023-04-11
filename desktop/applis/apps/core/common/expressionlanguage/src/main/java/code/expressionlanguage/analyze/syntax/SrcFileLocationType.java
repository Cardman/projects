package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.RootBlock;

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

    public RootBlock getType() {
        return type;
    }
}
