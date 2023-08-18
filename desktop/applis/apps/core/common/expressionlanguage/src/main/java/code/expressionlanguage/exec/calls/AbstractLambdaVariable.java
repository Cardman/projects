package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public abstract class AbstractLambdaVariable extends AbstractBasicReflectPageEl {

    private boolean calledAfter;
    private boolean visitablePage;
    private boolean visitedPage;

    protected AbstractLambdaVariable(boolean _lambda) {
        super(_lambda);
    }
    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        if (!visitablePage) {
            setWrapException(false);
            if (hasToExit(_context, _stack)) {
                possibleWrap(_stack);
                return false;
            }
        }
        visitablePage = true;
        if (_stack.getStopper().isStopAtRef(_context, _stack)) {
            return false;
        }
        visitedPage = true;
        _stack.getBreakPointInfo().getStackState().visitedNone();
        if (!calledAfter) {
            setWrapException(false);
            Argument arg_ = calculate(_context, _stack);
            calledAfter = true;
            if (_context.callsOrException(_stack)) {
                possibleWrap(_stack);
                return false;
            }
            setReturnedArgument(arg_);
        }
        return true;
    }

    private void possibleWrap(StackCall _stack) {
        setWrapException(_stack.calls());
    }

    public boolean isVisitablePage() {
        return visitablePage;
    }

    public boolean isVisitedPage() {
        return visitedPage;
    }

    abstract boolean hasToExit(ContextEl _context, StackCall _stack);
    abstract Argument calculate(ContextEl _context, StackCall _stack);

}
