package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CallingState;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;

public final class DefaultExiting implements AbstractExiting {
    private final ContextEl context;

    public DefaultExiting(ContextEl _context) {
        this.context = _context;
    }

    @Override
    public boolean hasToExit(StackCall _stack, GeneType _className) {
        return hasToExit(_stack, _className,null);
    }

    @Override
    public boolean hasToExit(StackCall _stack, GeneType _className, Argument _arg) {
        CallingState state_ = state(_stack, _className, _arg);
        if (state_ instanceof NotInitializedClass) {
            context.getLocks().initClass(((NotInitializedClass)state_).getRootBlock());
        }
        if (state_ != null) {
            _stack.setCallingState(state_);
            return true;
        }
        return false;
    }

    @Override
    public CallingState state(StackCall _stack, GeneType _className, Argument _arg) {
        return MetaInfoUtil.state(context,_className,_arg,_stack);
    }
}
