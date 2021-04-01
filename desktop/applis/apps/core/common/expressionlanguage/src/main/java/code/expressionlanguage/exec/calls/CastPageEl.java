package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.inherits.ExecInherits;

public final class CastPageEl extends AbstractMethodPageEl {
    public CastPageEl(Argument _gl, String _glClass) {
        super(_gl,_glClass);
    }

//    @Override
//    public String formatVarType(String _varType) {
//        return ExecInherits.quickFormat(getBlockRootType(),getGlobalClass(),_varType);
//    }
}
