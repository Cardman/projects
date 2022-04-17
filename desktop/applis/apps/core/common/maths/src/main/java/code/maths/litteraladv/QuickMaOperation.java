package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.StringMap;

public final class QuickMaOperation extends MethodMaOperation {
    private final boolean abs;
    public QuickMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op, boolean _abs) {
        super(_index, _indexChild, _m, _op);
        abs = _abs;
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        MaStruct f_ = MaNumParsers.tryGet(this,0);
        if (!(f_ instanceof MaBoolStruct)) {
            _error.setOffset(getIndexExp()+ StrTypes.offset(getOperats().getOpers(),0));
            return;
        }
        if (f_.sameReference(MaBoolStruct.of(abs))) {
            setStruct(f_);
            return;
        }
        MaStruct a_ = MaNumParsers.tryGet(this,1);
        if (!(a_ instanceof MaBoolStruct)) {
            _error.setOffset(getIndexExp()+ StrTypes.offset(getOperats().getOpers(),0));
            return;
        }
        setStruct(a_);
    }


    boolean isAbs() {
        return abs;
    }
}
