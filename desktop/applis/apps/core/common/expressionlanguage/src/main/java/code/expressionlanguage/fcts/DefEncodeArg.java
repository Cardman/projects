package code.expressionlanguage.fcts;

import code.expressionlanguage.structs.Struct;

public final class DefEncodeArg implements AbsEncodeArg {
    @Override
    public String baseEncode(Struct _arg, String _alpha) {
        return _alpha;
    }
}
