package code.maths.litteraladv;

import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbGeneMaOperation extends MethodMaOperation {
    private static final String TRUE = MaOperationsSequence.TRUE;
    private static final String FALSE = MaOperationsSequence.FALSE;
    private final String oper;
    public SymbGeneMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, String _ope) {
        super(_index, _indexChild, _m, _op);
        oper = _ope;
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq(oper, TRUE)) {
            setStruct(MaBoolStruct.of(true));
        }
        if (StringUtil.quickEq(oper, FALSE)) {
            setStruct(MaBoolStruct.of(false));
        }
    }

}
