package code.expressionlanguage.exec.blocks;

import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecAnonFctContent;

public final class ExecSwitchInstanceMethod extends ExecAbstractSwitchMethod {

    public ExecSwitchInstanceMethod(boolean _retRef, String _name, MethodAccessKind _modifier, String _importedParamType, String _retType, ExecAnonFctContent _anonFctContent) {
        super(_retRef, _name, _modifier, _importedParamType, _retType, _anonFctContent);
    }

}
