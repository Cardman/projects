package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;

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
        return MetaInfoUtil.hasToExitAfterInit(context,_className,_stack);
    }
}
