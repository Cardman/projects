package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.NotInitializedClass;

public abstract class AbstractLambdaVariable extends AbstractBasicReflectPageEl {

    private boolean calledAfter;
    private boolean visitedPage;

    protected AbstractLambdaVariable(boolean _lambda) {
        super(_lambda);
    }
    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (_stack.getStopper().isStopAtRef(_context, _stack)) {
            return false;
        }
        visitedPage = true;
        _stack.getBreakPointInfo().getStackState().visitedNone();
        if (!calledAfter) {
            setWrapException(false);
            Argument arg_ = prepare(_context, _stack);
            if (_stack.getCallingState() instanceof NotInitializedClass) {
                setWrapException(true);
                return false;
            }
            calledAfter = true;
            if (_context.callsOrException(_stack)) {
                setWrapException(_stack.calls());
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    public boolean isVisitedPage() {
        return visitedPage;
    }

    abstract Argument prepare(ContextEl _context, StackCall _stack);

}
