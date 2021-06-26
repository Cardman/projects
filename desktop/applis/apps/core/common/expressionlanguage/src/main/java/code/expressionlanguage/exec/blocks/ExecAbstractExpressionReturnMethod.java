package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.util.CustList;

public abstract class ExecAbstractExpressionReturnMethod extends ExecAbstractReturnMethod implements WithNotEmptyEl {
    private final ExecOperationNodeListOff exp;
    protected ExecAbstractExpressionReturnMethod(int _expressionOffset, CustList<ExecOperationNode> _opRet) {
        exp = new ExecOperationNodeListOff(_opRet,_expressionOffset);
    }

    @Override
    public void removeBlockFinally(ContextEl _conf, StackCall _stack) {
        tryReturn(_stack);
    }
    @Override
    public CustList<ExecOperationNode> getEl(ContextEl _context, int _indexProcess) {
        return exp.getList();
    }


    protected int getExpressionOffset() {
        return exp.getOffset();
    }
}
