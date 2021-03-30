package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;

public abstract class AbstractReflectPageEl extends AbstractPageEl implements ForwardPageEl {

    private boolean wrapException;

    private boolean lambda;

    @Override
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        setWrapper(_wrap);
        setReturnedArgument(_argument);
    }

    public boolean isWrapException() {
        return wrapException;
    }

    void setWrapException(boolean _wrapException) {
        if (_wrapException) {
            if (lambda) {
                wrapException = false;
                return;
            }
        }
        wrapException = _wrapException;
    }

    public void setLambda(boolean _lambda) {
        lambda = _lambda;
    }

}
