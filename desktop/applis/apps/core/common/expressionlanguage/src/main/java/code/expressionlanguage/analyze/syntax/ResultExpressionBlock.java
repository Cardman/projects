package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ResultExpressionBlock {
    private final SrcFileLocation caller;
    private final AbsBk block;
    private final ResultExpression res;
    private final int index;

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r) {
        this(_c,_b,_r,0);
    }

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r, int _i) {
        this.caller = _c;
        this.block = _b;
        this.res = _r;
        this.index = _i;
    }

    public SrcFileLocation getCaller() {
        return caller;
    }

    public ResultExpression getRes() {
        return res;
    }

    public int getIndex() {
        return index;
    }

    public AbsBk getBlock() {
        return block;
    }
}
