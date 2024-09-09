package code.expressionlanguage.exec.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractCommonMethodPageEl extends AbstractPageEl implements ForwardPageEl {

    protected AbstractCommonMethodPageEl(ExecFormattedRootBlock _glClass) {
        super(_glClass);
    }
    public void receive(AbstractWrapper _wrap, Struct _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
    }
}
