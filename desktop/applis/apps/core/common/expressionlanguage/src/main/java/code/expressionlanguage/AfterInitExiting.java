package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;

public final class AfterInitExiting implements AbstractExiting{
    private final ContextEl context;

    public AfterInitExiting(ContextEl _context) {
        this.context = _context;
    }
    @Override
    public boolean hasToExit(StackCall _stack, GeneType _className) {
        return hasToExit(_stack,_className,null);
    }

    @Override
    public boolean hasToExit(StackCall _stack, GeneType _className, Argument _arg) {
        CallingState state_ = state(_stack, _className, _arg);
        if (state_ != null) {
            _stack.setCallingState(state_);
            return true;
        }
        return false;
    }

    @Override
    public CallingState state(StackCall _stack, GeneType _className, Argument _arg) {
        return MetaInfoUtil.stateAfterInit(context,_className,_stack);
    }
}
