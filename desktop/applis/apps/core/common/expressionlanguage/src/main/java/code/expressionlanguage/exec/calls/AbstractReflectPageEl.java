package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.util.CustList;

public abstract class AbstractReflectPageEl extends AbstractPageEl implements ForwardPageEl {

    private final CustList<Argument> arguments;

    private boolean wrapException;

    private boolean lambda;

    protected AbstractReflectPageEl(CustList<Argument> _arguments) {
        arguments = _arguments;
    }
    @Override
    public void tryProcessEl(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public void receive(Argument _argument, ContextEl _context) {
        setReturnedArgument(_argument);
    }

    @Override
    public final void forwardTo(AbstractPageEl _page, ContextEl _context) {
        Argument a_ = getReturnedArgument();
        _page.receive(a_, _context);
    }

    public CustList<Argument> getArguments() {
        return arguments;
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
