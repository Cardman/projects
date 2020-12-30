package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.WithEl;

public abstract class AbstractMethodPageEl extends AbstractPageEl implements ForwardPageEl {

    protected AbstractMethodPageEl(Argument _gl, String _glClass) {
        setGlobalArgument(_gl);
        setGlobalClass(_glClass);
    }

    public void initReturnType(Argument _right) {
        if (_right != null) {
            setReturnedArgument(_right);
        }
    }

    @Override
    public boolean checkCondition(ContextEl _context, StackCall _stack) {
        return true;
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
