package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.inherits.ExecInherits;

public final class SwitchCastPageEl extends AbstractSwitchMethodPageEl {
    public SwitchCastPageEl(Argument _gl, String _glClass, Argument _value) {
        super(_gl, _glClass, _value);
    }

//    @Override
//    public String formatVarType(String _varType) {
//        return ExecInherits.quickFormat(getBlockRootType(),getGlobalClass(),_varType);
//    }

}
