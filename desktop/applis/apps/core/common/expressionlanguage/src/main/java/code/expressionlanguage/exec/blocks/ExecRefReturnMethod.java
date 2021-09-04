package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.opers.ExecOperationNode;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.CustList;
import code.util.core.IndexConstants;

public final class ExecRefReturnMethod extends ExecAbstractExpressionReturnMethod {

    public ExecRefReturnMethod(int _expressionOffset, CustList<ExecOperationNode> _opRet) {
        super(_expressionOffset,_opRet);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.globalOffset(getExpressionOffset());
        ArgumentsPair argumentsPair_ = ExecHelperBlocks.tryToCalculatePair(_cont, IndexConstants.FIRST_INDEX, _stack, getExp(), 0, this);
        if (argumentsPair_ == null) {
            return;
        }
        ip_.clearCurrentEls();
        ip_.setWrapper(argumentsPair_.getWrapper());
        ip_.setReturnedArgument(argumentsPair_.getArgument());
        tryReturn(_stack);
    }

}
