package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.WithEl;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;

public abstract class AbstractCommonMethodPageEl extends AbstractPageEl implements ForwardPageEl {

    protected AbstractCommonMethodPageEl(ExecFormattedRootBlock _glClass) {
        super(_glClass);
    }
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        basicReceive(_wrap, _argument,_context, _stack);
    }
    protected void processTagsBase(ContextEl _context, StackCall _stack, ExecBlock _en){
        //method walk through
        if (_en instanceof WithEl) {
            setGlobalOffset(_en.getOffsetTrim());
            setOffset(0);
            ((WithEl)_en).processEl(_context, _stack);
            return;
        }
        setNullReadWrite();
    }
}
