package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ResultExpressionBlock {
    private final SrcFileLocation caller;
    private final AbsBk block;
    private final ResultExpression res;
    private final int begin;
    private final int end;

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r) {
        this(_c,_b,_r,-1,-1);
    }

    public ResultExpressionBlock(SrcFileLocation _c,AbsBk _b, ResultExpression _r, int _beg, int _end) {
        this.caller = _c;
        this.block = _b;
        this.res = _r;
        this.begin = _beg;
        this.end = _end;
    }

    public SrcFileLocation getCaller() {
        return caller;
    }

    public ResultExpression getRes() {
        return res;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

    public AbsBk getBlock() {
        return block;
    }
}
