package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.opers.ExecOperationNode;

public final class LambdaRdCodRefectMethodPageEl extends AbstractBasicReflectPageEl {

    private final Argument argument;
    private boolean called;

    public LambdaRdCodRefectMethodPageEl(Argument _argument) {
        argument = _argument;
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (called) {
            return true;
        }
        called = true;
        Argument arg_ = ExecOperationNode.processRandCode(argument, _context, _stack);
        if (_context.callsOrException(_stack)) {
            return false;
        }
        setReturnedArgument(arg_);
        return true;
    }
}
