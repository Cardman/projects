package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public abstract class AbstractLambdaVariable extends AbstractBasicReflectPageEl {

    private boolean calledAfter;
    private boolean checkField;
    private boolean checkingParent;
    private boolean exiting;

    protected AbstractLambdaVariable(boolean _lambda) {
        super(_lambda);
    }
    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        setWrapException(false);
        if (hasToExit(_context, _stack)) {
            possibleWrap(_stack);
            return false;
        }
        checkingParent = true;
        if (koParent(_context, _stack)) {
            checkingParent = _stack.trueException() == null;
            return false;
        }
        checkingParent = false;
        checkField = true;
        if (_stack.getStopper().isStopAtRef(_context, _stack)) {
            return false;
        }
        checkField = false;
        if (!calledAfter) {
            setWrapException(false);
            Argument arg_ = calculate(_context, _stack);
            calledAfter = true;
            if (_context.callsOrException(_stack)) {
                possibleWrap(_stack);
                return false;
            }
            setReturnedArgument(arg_);
            checkField = true;
            exiting = true;
            if (_stack.getStopper().isStopAtRef(_context, _stack)) {
                return false;
            }
            checkField = false;
            exiting = false;
            return true;
        }
        if (!exiting) {
            checkField = true;
            exiting = true;
            if (_stack.getStopper().isStopAtRef(_context, _stack)) {
                return false;
            }
            checkField = false;
        }
        return true;
    }

    protected abstract boolean koParent(ContextEl _context, StackCall _stack);

    private void possibleWrap(StackCall _stack) {
        setWrapException(_stack.calls());
    }

    public boolean isExiting() {
        return exiting;
    }

    public boolean isCheckField() {
        return checkField;
    }

    public boolean isCheckingParent() {
        return checkingParent;
    }

    abstract boolean hasToExit(ContextEl _context, StackCall _stack);
    abstract Argument calculate(ContextEl _context, StackCall _stack);

}
