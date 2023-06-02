package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public abstract class ExecAbstractExpressionReturnMethod extends ExecAbstractReturnMethod implements WithEl {
    private final ExecOperationNodeListOff exp;
    protected ExecAbstractExpressionReturnMethod(int _expressionOffset, CustList<ExecOperationNode> _opRet) {
        exp = new ExecOperationNodeListOff(_opRet,_expressionOffset);
    }

    public CustList<ExecOperationNode> getExp() {
        return exp.getList();
    }

    public int getExpressionOffset() {
        return exp.getOffset();
    }
}
