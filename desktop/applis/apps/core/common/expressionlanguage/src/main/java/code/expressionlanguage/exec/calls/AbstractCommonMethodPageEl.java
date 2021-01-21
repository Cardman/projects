package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.WithEl;

public abstract class AbstractCommonMethodPageEl extends AbstractPageEl implements ForwardPageEl {

    protected AbstractCommonMethodPageEl(Argument _gl, String _glClass) {
        setGlobalArgument(_gl);
        setGlobalClass(_glClass);
    }

    @Override
    public void tryProcessEl(ContextEl _context, StackCall _stack) {
        //method walk through
        ExecBlock en_ = getBlock();
        if (en_ instanceof WithEl) {
            ((WithEl)en_).processEl(_context, _stack);
            return;
        }
        setNullReadWrite();
    }

}
