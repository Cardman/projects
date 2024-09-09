package code.expressionlanguage.exec.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.structs.Struct;

public final class StrNativeFct implements NativeFct {

    @Override
    public Struct compute(Struct _arg, ContextEl _conf) {
        return ExecCatOperation.getDisplayable(_arg, _conf);
    }
}
