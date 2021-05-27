package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecCatOperation;
import code.expressionlanguage.exec.util.NativeFct;
import code.expressionlanguage.structs.Struct;

public final class RendNativeFct implements NativeFct {

    @Override
    public Struct compute(Argument _arg, ContextEl _conf) {
        return ExecCatOperation.getDisplayable(_arg, _conf);
    }
}
