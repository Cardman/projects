package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;

public final class CastPageEl extends AbstractMethodPageEl {
    public CastPageEl(ContextEl _context, String _ret, Argument _gl, String _glClass) {
        super(_context,_ret,_gl,_glClass);
    }

    @Override
    public String formatVarType(String _varType, ContextEl _cont) {
        return ExecTemplates.quickFormat(getGlobalClass(),_varType,_cont);
    }
}
