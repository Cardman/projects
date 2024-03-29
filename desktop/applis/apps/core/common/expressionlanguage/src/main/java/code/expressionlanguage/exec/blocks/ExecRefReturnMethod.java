package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.core.IndexConstants;

public final class ExecRefReturnMethod extends ExecAbstractExpressionReturnMethod {

    public ExecRefReturnMethod(ExecOperationNodeListOff _ex) {
        super(_ex);
    }

    @Override
    public void processEl(ContextEl _cont, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        ip_.globalOffset(getExpressionOffset());
        ArgumentsPair argumentsPair_ = ExecHelperBlocks.tryToCalculatePair(_cont, IndexConstants.FIRST_INDEX, _stack, exp(), 0, this,getExp().getEnd());
        if (_stack.stopAt(_cont)) {
            return;
        }
        ip_.clearCurrentEls();
        if (argumentsPair_ != null) {
            ip_.setWrapper(argumentsPair_.getWrapper());
            ip_.setReturnedArgument(argumentsPair_.getArgument());
        }
        tryReturn(_stack,ip_);
    }

}
