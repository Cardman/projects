package code.expressionlanguage;

import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.exec.StackCall;

public final class DefaultExiting implements AbstractExiting {
    private final ContextEl context;

    public DefaultExiting(ContextEl _context) {
        this.context = _context;
    }

    @Override
    public boolean hasToExit(StackCall _stack, String _className) {
        return hasToExit(_stack, _className,null);
    }

    @Override
    public boolean hasToExit(StackCall _stack, String _className, Argument _arg) {
        return ExecutingUtil.hasToExit(context,_className,_arg, _stack);
    }
}
