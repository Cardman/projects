package code.expressionlanguage.fcts;

import code.expressionlanguage.structs.NumberStruct;
import code.expressionlanguage.structs.Struct;

public final class ArgRadix implements AbsRadix {
    @Override
    public int radix(Struct _arg) {
        return ((NumberStruct)_arg).intStruct();
    }
}
