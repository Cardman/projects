package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.WithEl;
import code.expressionlanguage.exec.variables.AbstractWrapper;

public final class BlockPageEl extends AbstractPageEl {
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
    }
    @Override
    public void processTagsBase(ContextEl _context, StackCall _stack){
        //static block or instance block walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof WithEl) {
            setGlobalOffset(en_.getOffsetTrim());
            setOffset(0);
            ((WithEl)en_).processEl(_context, _stack);
            return;
        }
        setNullReadWrite();
    }

}
