package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public abstract class AbstractLambdaVariable extends AbstractBasicReflectPageEl {

    private boolean calledAfter;
    private boolean checkField;
    private boolean checkingParent;
    private boolean exiting;
    private final AbstractInitClass initClass;

    protected AbstractLambdaVariable(boolean _lambda, AbstractInitClass _init) {
        super(_lambda);
        initClass = _init;
    }

    protected AbstractLambdaVariable(boolean _lambda, ReflectFieldContent _init) {
        super(_lambda);
        initClass = new FieldInitClass(_init);
    }
    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        setWrapException(false);
        if (initClass.hasToExit(this,_context, _stack)) {
            possibleWrap(_stack);
            return false;
        }
        checkingParent = true;
        if (initClass.koParent(this,_context, _stack)) {
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

    abstract Argument calculate(ContextEl _context, StackCall _stack);

}
