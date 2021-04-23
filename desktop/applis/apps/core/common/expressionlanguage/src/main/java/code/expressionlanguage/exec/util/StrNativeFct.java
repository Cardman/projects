package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.structs.Struct;

public final class StrNativeFct implements NativeFct {

    @Override
    public Struct compute(Argument _arg, ContextEl _conf) {
        return ExecCatOperation.getDisplayable(_arg, _conf);
    }
}
