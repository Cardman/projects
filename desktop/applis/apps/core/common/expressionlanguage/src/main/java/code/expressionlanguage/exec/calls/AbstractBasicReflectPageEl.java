package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractBasicReflectPageEl extends AbstractReflectPageEl {
    protected AbstractBasicReflectPageEl(boolean _lambda) {
        super(_lambda);
    }

    @Override
    public void receive(AbstractWrapper _wrap, Struct _argument, ContextEl _context, StackCall _stack) {
        setWrapper(_wrap);
        setReturnedArgument(_argument);
    }
}
