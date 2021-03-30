package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecAbstractSwitchMethod;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.WithEl;
import code.expressionlanguage.exec.stacks.SwitchBlockStack;

public abstract class AbstractCommonMethodPageEl extends AbstractPageEl implements ForwardPageEl {

    protected AbstractCommonMethodPageEl(Argument _gl, String _glClass) {
        setGlobalArgument(_gl);
        setGlobalClass(_glClass);
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
