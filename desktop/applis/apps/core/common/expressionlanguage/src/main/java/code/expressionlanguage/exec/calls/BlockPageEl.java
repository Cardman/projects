package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;

public final class BlockPageEl extends AbstractPageEl {
    public BlockPageEl(ExecFormattedRootBlock _global) {
        super(_global);
    }

    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        //static block or instance block walk through
        commonTageBase(_context,_stack,null);
    }

}
