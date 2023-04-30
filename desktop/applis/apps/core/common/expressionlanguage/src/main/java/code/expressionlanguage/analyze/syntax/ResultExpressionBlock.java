package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ResultExpressionBlock {
    private final SrcFileLocation caller;
    private final AbsBk block;
    private final ResultExpression res;

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r) {
        this.caller = _c;
        this.block = _b;
        this.res = _r;
    }

    public SrcFileLocation getCaller() {
        return caller;
    }

    public ResultExpression getRes() {
        return res;
    }

    public AbsBk getBlock() {
        return block;
    }
}
