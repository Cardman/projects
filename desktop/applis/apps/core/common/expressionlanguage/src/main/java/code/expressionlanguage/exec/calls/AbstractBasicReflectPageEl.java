package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;

public abstract class AbstractBasicReflectPageEl extends AbstractReflectPageEl {
    protected AbstractBasicReflectPageEl(boolean _lambda) {
        super(_lambda);
    }

    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack) {
        if (!checkCondition(_context, _stack)) {
            return;
        }
        setNullReadWrite();
    }
    @Override
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        setWrapper(_wrap);
        setReturnedArgument(_argument);
    }
    public abstract boolean checkCondition(ContextEl _context, StackCall _stack);
}
