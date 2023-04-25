package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.blocks.AbsBk;

public final class ResultExpressionBlock {
    private final AbsBk block;
    private final ResultExpression res;

    public ResultExpressionBlock(AbsBk _b, ResultExpression _r) {
        this.block = _b;
        this.res = _r;
    }

    public ResultExpression getRes() {
        return res;
    }

    public AbsBk getBlock() {
        return block;
    }
}
