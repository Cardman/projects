package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class AbsBkSrcFileLocation {
    private final SrcFileLocation caller;
    private final AbsBk block;

    public AbsBkSrcFileLocation(SrcFileLocation _c, AbsBk _b) {
        this.caller = _c;
        this.block = _b;
    }

    public AbsBk getBlock() {
        return block;
    }

    public SrcFileLocation getCaller() {
        return caller;
    }
}
