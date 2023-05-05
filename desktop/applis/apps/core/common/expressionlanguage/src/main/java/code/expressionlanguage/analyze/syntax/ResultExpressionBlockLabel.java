package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ResultExpressionBlockLabel {
    private final SrcFileLocation caller;
    private final AbsBk block;

    public ResultExpressionBlockLabel(SrcFileLocation _c, AbsBk _b) {
        this.caller = _c;
        this.block = _b;
    }

    public SrcFileLocation getCaller() {
        return caller;
    }

    public AbsBk getBlock() {
        return block;
    }
}
