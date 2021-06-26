package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExpressionLanguage;
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
        ip_.setOffset(0);
        ip_.setGlobalOffset(getExpressionOffset());
        ExpressionLanguage el_ = ip_.getCurrentEl(_cont,this, IndexConstants.FIRST_INDEX, IndexConstants.FIRST_INDEX);
        ArgumentsPair argumentsPair_ = ExpressionLanguage.tryToCalculatePair(_cont, el_, 0, _stack);
        if (argumentsPair_ == null) {
            return;
        }
        ip_.clearCurrentEls();
        ip_.setWrapper(argumentsPair_.getWrapper());
        ip_.setReturnedArgument(argumentsPair_.getArgument());
        tryReturn(_stack);
    }

}
