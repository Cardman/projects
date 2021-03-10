package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.StringMap;
import code.util.core.StringUtil;

public final class SymbGeneMaOperation extends MethodMaOperation {
    private String oper = "";
    public SymbGeneMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        if (StringUtil.quickEq(oper,"&")) {
            setStruct(MaBoolStruct.of(true));
        }
        if (StringUtil.quickEq(oper,"|")) {
            setStruct(MaBoolStruct.of(false));
        }
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        oper = vs_.lastValue();
        vs_.remove(vs_.size()-1);
        vs_.remove(0);
        getChs().addAllEntries(vs_);
    }
}
