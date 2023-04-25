package code.expressionlanguage.analyze.syntax;

import code.expressionlanguage.analyze.opers.OperationNode;

public final class ResultExpressionBlockOperation {
    private final OperationNode block;
    private final ResultExpressionBlock res;

    public ResultExpressionBlockOperation(OperationNode _b, ResultExpressionBlock _r) {
        this.block = _b;
        this.res = _r;
    }

    public ResultExpressionBlock getRes() {
        return res;
    }

    public OperationNode getBlock() {
        return block;
    }
}
