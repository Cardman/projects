package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;

public final class MethodPageEl extends AbstractMethodPageEl {


    public MethodPageEl(ContextEl _context, String _ret, Argument _gl, String _glClass, Argument _right) {
        super(_context,_ret,_gl,_glClass);
        if (_right != null) {
            setReturnedArgument(_right);
        }
    }

}
