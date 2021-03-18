package code.maths.litteraladv;

import code.maths.litteralcom.StrTypes;
import code.util.StringMap;

public final class UnaryBooleanMaOperation extends MethodMaOperation {
    public UnaryBooleanMaOperation(int _index, int _indexChild, MethodMaOperation _m, MaOperationsSequence _op) {
        super(_index, _indexChild, _m, _op);
    }

    @Override
    void calculate(StringMap<MaStruct> _conf, MaError _error, MaDelimiters _del) {
        MaStruct value_ = MaNumParsers.tryGet(this, 0);
        if (!(value_ instanceof MaBoolStruct)) {
            _error.setOffset(getIndexExp()+StrTypes.offset(getOperats().getOpers(),0));
            return;
        }
        setStruct(((MaBoolStruct)value_).neg());
    }

    @Override
    void calculate() {
        StrTypes vs_ = getOperats().getParts();
        getChs().addAllEntries(vs_);
    }
}
