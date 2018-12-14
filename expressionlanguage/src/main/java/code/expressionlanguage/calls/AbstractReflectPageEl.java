package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.util.ParentStackBlock;
import code.util.CustList;

public abstract class AbstractReflectPageEl extends AbstractPageEl implements ForwardPageEl {

    private Argument returnedArgument;

    private CustList<Argument> arguments;

    private boolean wrapException;

    private boolean lambda;

    @Override
    public Argument getReturnedArgument() {
        return returnedArgument;
    }

    @Override
    public void setReturnedArgument(Argument _returnedArgument) {
        returnedArgument = _returnedArgument;
    }

    @Override
    public void setReturnedArgument() {
        Argument void_ = Argument.createVoid();
        returnedArgument = void_;
    }

    @Override
    public void tryProcessEl(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public void postReturn(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public ParentStackBlock getNextBlock(Block _block, ContextEl _context) {
        return null;
    }

    @Override
    public void postBlock(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public void endRoot(ContextEl _context) {
        setNullReadWrite();
    }

    @Override
    public boolean receive(Argument _argument, ContextEl _context) {
        returnedArgument = _argument;
        return true;
    }

    @Override
    public final boolean forwardTo(AbstractPageEl _page, ContextEl _context) {
        Argument a_ = getReturnedArgument();
        return _page.receive(a_, _context);
    }

    public CustList<Argument> getArguments() {
        return arguments;
    }

    public void setArguments(CustList<Argument> _arguments) {
        arguments = _arguments;
    }

    public boolean isWrapException() {
        return wrapException;
    }

    public void setWrapException(boolean _wrapException) {
        if (_wrapException) {
            if (lambda) {
                wrapException = false;
                return;
            }
        }
        wrapException = _wrapException;
    }

    public boolean isLambda() {
        return lambda;
    }

    public void setLambda(boolean _lambda) {
        lambda = _lambda;
    }

}
