package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;

public final class LambdaToStrRefectMethodPageEl extends AbstractBasicReflectPageEl {

    private final Argument argument;
    private boolean called;

    public LambdaToStrRefectMethodPageEl(Argument _argument) {
        argument = _argument;
        setLambda(false);
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (called) {
            return true;
        }
        called = true;
        Argument arg_ = IndirectCalledFctUtil.processString(argument, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }

}
