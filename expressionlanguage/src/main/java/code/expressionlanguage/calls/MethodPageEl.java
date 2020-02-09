package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.calls.util.ReadWrite;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.BracedBlock;
import code.expressionlanguage.methods.WithEl;
import code.expressionlanguage.methods.util.ParentStackBlock;

public final class MethodPageEl extends AbstractMethodPageEl {

    private Argument rightArgument;

    public MethodPageEl(ContextEl _context, String _ret, Argument _gl, String _glClass, Argument _right) {
        super(_context,_ret,_gl,_glClass);
        rightArgument = _right;
        if (_right != null) {
            setReturnedArgument(_right);
        }
    }

    public Argument getRightArgument() {
        return rightArgument;
    }

}
