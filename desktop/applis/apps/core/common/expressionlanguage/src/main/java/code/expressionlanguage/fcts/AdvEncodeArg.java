package code.expressionlanguage.fcts;

import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.Struct;

public final class AdvEncodeArg implements AbsEncodeArg {
    @Override
    public String baseEncode(Struct _arg, String _alpha) {
        return NumParsers.base(_arg, _alpha);
    }
}
