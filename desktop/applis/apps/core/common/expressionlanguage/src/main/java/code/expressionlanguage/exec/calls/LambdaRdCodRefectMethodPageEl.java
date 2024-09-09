package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.IndirectCalledFctUtil;
import code.expressionlanguage.structs.Struct;

public final class LambdaRdCodRefectMethodPageEl extends AbstractBasicReflectPageEl {

    private final Struct argument;
    private boolean called;

    public LambdaRdCodRefectMethodPageEl(Struct _argument) {
        super(false);
        argument = _argument;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (called) {
            return true;
        }
        called = true;
        Struct arg_ = IndirectCalledFctUtil.processRandCode(argument, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }
}
