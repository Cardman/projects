package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public abstract class ExecAbstractExpressionReturnMethod extends ExecAbstractReturnMethod implements WithEl {
    private final ExecOperationNodeListOff exp;
    protected ExecAbstractExpressionReturnMethod(int _expressionOffset, CustList<ExecOperationNode> _opRet) {
        exp = new ExecOperationNodeListOff(_opRet,_expressionOffset);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf, StackCall _stack) {
        tryReturn(_stack);
    }

    public CustList<ExecOperationNode> getExp() {
        return exp.getList();
    }

    protected int getExpressionOffset() {
        return exp.getOffset();
    }
}
