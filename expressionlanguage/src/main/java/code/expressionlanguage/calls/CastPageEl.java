package code.expressionlanguage.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Templates;

public final class CastPageEl extends AbstractMethodPageEl {
    public CastPageEl(ContextEl _context, String _ret, Argument _gl, String _glClass) {
        super(_context,_ret,_gl,_glClass);
    }

    @Override
    public String formatVarType(String _varType, ExecutableCode _cont) {
        return Templates.quickFormat(getGlobalClass(),_varType,_cont);
    }
}
