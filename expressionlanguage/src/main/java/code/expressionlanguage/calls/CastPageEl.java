package code.expressionlanguage.calls;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.inherits.Templates;

public final class CastPageEl extends AbstractMethodPageEl {
    public CastPageEl(ContextEl _context) {
        super(_context);
    }

    @Override
    public String formatVarType(String _varType, ExecutableCode _cont) {
        return Templates.format(getGlobalClass(),_varType,_cont);
    }
}
