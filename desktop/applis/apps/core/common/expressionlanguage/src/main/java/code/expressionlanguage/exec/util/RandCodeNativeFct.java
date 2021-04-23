package code.expressionlanguage.exec.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.Struct;

public final class RandCodeNativeFct implements NativeFct {
    @Override
    public Struct compute(Argument _arg, ContextEl _conf) {
        return new LongStruct(_arg.getStruct().randCode());
    }
}
