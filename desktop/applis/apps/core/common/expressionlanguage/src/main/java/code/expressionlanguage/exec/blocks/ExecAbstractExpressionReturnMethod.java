package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public abstract class ExecAbstractExpressionReturnMethod extends ExecAbstractReturnMethod implements WithEl {
    private final ExecOperationNodeListOff exp;

    protected ExecAbstractExpressionReturnMethod(ExecOperationNodeListOff _ex) {
        exp = _ex;
    }

    public ExecOperationNodeListOff getExp() {
        return exp;
    }

    public CustList<ExecOperationNode> exp() {
        return exp.getList();
    }

    public int getExpressionOffset() {
        return exp.getOffset();
    }
}
