package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ArgumentListCall;

public abstract class AbstractLambdaVariable extends AbstractBasicReflectPageEl {
    private final ArgumentListCall array;

    private boolean calledAfter;

    protected AbstractLambdaVariable(ArgumentListCall _array) {
        array = _array;
        setLambda(true);
    }
    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!calledAfter) {
            setWrapException(false);
            Argument arg_ = prepare(_context, array, _stack);
            calledAfter = true;
            if (_context.callsOrException(_stack)) {
                setWrapException(_stack.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    abstract Argument prepare(ContextEl _context, ArgumentListCall _array, StackCall _stack);
}
